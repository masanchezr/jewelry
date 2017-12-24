package com.je.services.jewels;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.je.dbaccess.entities.JewelEntity;
import com.je.dbaccess.entities.MetalEntity;
import com.je.dbaccess.entities.PlaceEntity;
import com.je.dbaccess.managers.JewelsManager;
import com.je.utils.constants.Constants;
import com.je.utils.string.Util;

/**
 * The Class JewelServiceImpl.
 */
public class JewelServiceImpl implements JewelService {

	/** The jewels manager. */
	@Autowired
	private JewelsManager jewelsManager;

	/** The mapper. */
	@Autowired
	private Mapper mapper;

	/**
	 * Adds the jewel.
	 * 
	 * @param thing
	 *            the thing
	 */

	@Override
	public JewelEntity addObject(JewelEntity thing) {
		if (Util.isEmpty(thing.getImg())) {
			thing.setImg(null);
		}
		// getBarCode(jewel);
		return jewelsManager.findById(jewelsManager.save(thing).getIdjewel());
	}

	private void getBarCode(JewelEntity jewel) {
		// intento crear código de barras
		try {
			String barCodePath = System.getenv(Constants.OPENSHIFT_DATA_DIR);
			// String barCodePath = "D:/";
			// puede ser con 128 o 39 no se cual es la diferencia
			Code39Bean bean = new Code39Bean();
			final int dpi = 160;
			// Configure the barcode generator
			bean.setModuleWidth(1.0);
			// altura de la barra
			// bean.setBarHeight(25.0);
			bean.setFontSize(5.0);
			bean.setQuietZone(2.0);
			bean.doQuietZone(true);
			// altura de la imagen bean.setHeight(30);
			// bean.setVerticalQuietZone(-6);
			// Open output file
			File outputFile = new File(barCodePath.concat(jewel.getReference()).concat(Constants.PNG));
			FileOutputStream out = new FileOutputStream(outputFile);
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);
			// Generate the barcode
			bean.generateBarcode(canvas, jewel.getReference().toUpperCase());
			// Signal end of generation
			canvas.finish();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public Collection<JewelEntity> searchAll() {
		return jewelsManager.searchAll();
	}

	@Override
	public JewelEntity selectProduct(Long idjewel) {
		return jewelsManager.searchById(idjewel);
	}

	@Override
	public Page<JewelEntity> searchAllActive(int pageNumber) {
		return jewelsManager.searchAllActivePageable(PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE));
	}

	@Override
	public Page<JewelEntity> searchPageableByPlace(int pageNumber, PlaceEntity place) {
		return jewelsManager.findByPlaceAndActiveTrue(place, PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE));
	}

	/**
	 * Búsqueda por categoría activa, joya activa y con imagen
	 */
	@Override
	public List<JewelEntity> searchJewelsByKeyWordCategory(String keywordcategory) {
		return jewelsManager.searchByCategoryActive(keywordcategory);
	}

	@Override
	public List<JewelEntity> search(JewelEntity jewelForm) {
		List<JewelEntity> jewels = null;
		String reference = jewelForm.getReference();
		if (!Util.isEmpty(reference)) {
			if (jewelForm.getCategory().getIdcategory().equals(Constants.ALLCATEGORIES)) {
				jewels = jewelsManager.searchByReferenceAndPlaceAndMetal(reference,
						mapper.map(jewelForm.getPlace(), PlaceEntity.class),
						mapper.map(jewelForm.getMetal(), MetalEntity.class));
			} else {
				jewels = new ArrayList<JewelEntity>();
				JewelEntity jewel = jewelsManager
						.searchByReferenceCategoryMetalPlace(mapper.map(jewelForm, JewelEntity.class));
				if (jewel != null) {
					jewels.add(mapper.map(jewel, JewelEntity.class));
				}
			}
		} else if (jewelForm.getCategory().getIdcategory().equals(Constants.ALLCATEGORIES)) {
			jewels = jewelsManager.searchByPlaceAndMetal(jewelForm.getPlace(), jewelForm.getMetal());
		} else {
			jewels = jewelsManager.searchByPlaceAndMetalAndCategory(jewelForm.getPlace(), jewelForm.getMetal(),
					jewelForm.getCategory());
		}
		return jewels;
	}

	@Override
	public void updateJewelEntity(JewelEntity jewelForm) {
		jewelsManager.save(jewelForm);
	}

	@Override
	public JewelEntity searchByReferenceCategoryMetalPlaceActive(JewelEntity jewel) {
		return jewelsManager.searchByReferenceCategoryMetalPlaceActive(jewel);
	}

	@Override
	public JewelEntity searchByReferenceCategoryMetalPlace(JewelEntity jewel) {
		return jewelsManager.searchByReferenceCategoryMetalPlace(jewel);
	}

	@Override
	public List<JewelEntity> searchByReferenceAndCategory(JewelEntity jewelForm) {
		return jewelsManager.searchByReferenceAndCategory(jewelForm);
	}

	@Override
	public List<JewelEntity> searchAllActiveByPlace(JewelEntity jewel) {
		return jewelsManager.searchAllActiveByPlace(jewel);
	}

	@Override
	public List<JewelEntity> searchByReference(JewelEntity jewel) {
		return jewelsManager.searchByReference(jewel.getReference());
	}

	@Override
	public void revise(JewelEntity jewel) {
		JewelEntity jewelEntity = jewelsManager.searchById(jewel.getIdjewel());
		jewelEntity.setRevised(Boolean.TRUE);
		jewelsManager.save(jewelEntity);

	}

	@Override
	public Page<JewelEntity> searchWithImg(int i) {
		return jewelsManager.searchWithImg(PageRequest.of(i - 1, Constants.PAGE_SIZE));
	}

	@Override
	public void setGramsGold() {
		jewelsManager.setGrams();
	}
}
