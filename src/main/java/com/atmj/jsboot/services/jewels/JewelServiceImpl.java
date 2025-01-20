package com.atmj.jsboot.services.jewels;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.atmj.jsboot.dbaccess.entities.CategoryEntity;
import com.atmj.jsboot.dbaccess.entities.JewelEntity;
import com.atmj.jsboot.dbaccess.entities.MetalEntity;
import com.atmj.jsboot.dbaccess.entities.PlaceEntity;
import com.atmj.jsboot.dbaccess.managers.JewelsManager;
import com.atmj.jsboot.utils.constants.Constants;
import com.atmj.jsboot.utils.date.DateUtil;
import com.atmj.jsboot.utils.string.Util;
import com.atmj.services.MetadataService;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

/**
 * The Class JewelServiceImpl.
 */
@Service
public class JewelServiceImpl implements JewelService {

	/** The jewels manager. */
	@Autowired
	private JewelsManager jewelsManager;

	@Autowired
	private MetadataService metadataservice;

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(JewelServiceImpl.class);

	/**
	 * Adds the jewel.
	 * 
	 * @param thing the thing
	 */

	@Override
	public JewelEntity addObject(JewelEntity thing) {
		if (Util.isEmpty(thing.getImg())) {
			thing.setImg(null);
		}
		return jewelsManager.findById(jewelsManager.save(thing).getIdjewel());
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
				jewels = new ArrayList<>();
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
	public Page<JewelEntity> searchActive(int i) {
		return jewelsManager.searchActive(PageRequest.of(i - 1, Constants.PAGE_SIZE));
	}

	public List<JewelEntity> searchJewels(List<JewelEntity> jewels, PlaceEntity place) {
		JewelEntity jewel;
		List<JewelEntity> newjewels = new ArrayList<>();
		Iterator<JewelEntity> ijewels = jewels.iterator();
		while (ijewels.hasNext() && newjewels != null) {
			jewel = ijewels.next();
			if (!Util.isEmpty(jewel.getReference())) {
				jewel.setPlace(place);
				jewel.setActive(Boolean.TRUE);
				jewel = searchByReferenceCategoryMetalPlaceActive(jewel);
				if (jewel != null && jewel.getIdjewel() != null) {
					newjewels.add(jewel);
				} else {
					newjewels = null;
				}
			}
		}
		return newjewels;
	}

	@Override
	public Page<JewelEntity> searchJewelsByCategory(CategoryEntity category, int i) {
		return jewelsManager.searchByCategoryActive(category, PageRequest.of(i - 1, Constants.PAGE_SIZE));
	}

	@Override
	public List<JewelEntity> searchByPrice(JewelEntity jewel) {
		String reference = jewel.getReference();
		BigDecimal price = jewel.getPrice();
		if (!Util.isEmpty(reference)) {
			if (price != null) {
				return jewelsManager.searchByPriceAndReference(price, reference);
			} else {
				return jewelsManager.searchByReference(reference);
			}
		} else {
			return jewelsManager.searchByPrice(price);
		}
	}

	@Override
	public Map<String, Object> searchByPlaceAndDates(PlaceEntity place, String sfrom, String suntil) {
		Map<String, Object> map = new HashMap<>();
		map.put("jewels", jewelsManager.findByPlaceAndBetweenCreationdate(place, DateUtil.getDate(sfrom),
				DateUtil.getDate(suntil)));
		map.put("sumgrams", jewelsManager.sumGramsByPlaceAndBetweenCreationdate(place, DateUtil.getDate(sfrom),
				DateUtil.getDate(suntil)));
		return map;
	}

	@Override
	public File generatePdf(PlaceEntity place, String datefrom, String dateuntil) {
		String path = metadataservice.getValue("DATA_DIR");
		List<JewelEntity> jewels = jewelsManager.findByPlaceAndBetweenCreationdate(place, DateUtil.getDate(datefrom),
				DateUtil.getDate(dateuntil));
		Iterator<JewelEntity> ijewels = jewels.iterator();
		// no puedo meter en el nombre del fichero el lugar de las joyas porque viene
		// vacío, solo viene el id
		File file = new File(path.concat("\\").concat(place.getIdplace().toString())
				.concat(DateUtil.getStringDateFormatddMMyyyy(new Date()).concat(".pdf")));
		file.setWritable(true);
		try (PdfWriter writer = new PdfWriter(file)) {
			PdfDocument pdf = new PdfDocument(writer);
			PageSize page = PageSize.A4.rotate();
			Document document = new Document(pdf, page);
			PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD, PdfEncodings.WINANSI);
			// Creating a table
			float[] pointColumnWidths = { 80F, 80F, 80F, 80F, 80F, 80F, 80F, 80F, 80F, 80F };
			Table table = new Table(pointColumnWidths);
			JewelEntity jewel;
			BigDecimal grams = BigDecimal.ZERO, jgrams;
			table.addHeaderCell("Referencia").setFont(font);
			table.addHeaderCell("Nombre").setFont(font);
			table.addHeaderCell("Descripción").setFont(font);
			table.addHeaderCell("Precio €").setFont(font);
			table.addHeaderCell("Categoría").setFont(font);
			table.addHeaderCell("Metal").setFont(font);
			table.addHeaderCell("Lugar").setFont(font);
			table.addHeaderCell("Activo").setFont(font);
			table.addHeaderCell("Fecha Envío").setFont(font);
			table.addHeaderCell("Gramos").setFont(font);
			while (ijewels.hasNext()) {
				jewel = ijewels.next();
				jgrams = jewel.getGrams();
				table.startNewRow();
				table.addCell(jewel.getReference());
				table.addCell(jewel.getName());
				table.addCell(jewel.getDescription());
				table.addCell(jewel.getPrice().toString());
				table.addCell(jewel.getCategory().getNamecategory());
				table.addCell(jewel.getMetal().getDescription());
				table.addCell(jewel.getPlace().getDescription());
				table.addCell(jewel.getActive().toString());
				table.addCell(DateUtil.getStringDateddMMyyyy(jewel.getCreationdate()));
				if (jgrams != null) {
					grams = grams.add(jgrams);
					table.addCell(String.valueOf(jgrams));
				}
			}
			document.add(table);
			document.add(new Paragraph("Gramos totales ".concat(grams.toString())).setFont(font).setFontSize(14)
					.setTextAlignment(TextAlignment.CENTER));
			document.close();
		} catch (IOException e) {
			log.error("No se ha podido generar el documento.");
		}
		return file;
	}
}
