package com.atmj.jsboot.web.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.atmj.jsboot.utils.constants.ConstantsViews;

/**
 * The Class MessageForm.
 */
public class MessageForm {

	/** The name. */
	@NotNull(message = ConstantsViews.ERRORSELECTNAME)
	@NotEmpty(message = ConstantsViews.ERRORSELECTNAME)
	private String name;

	/** The email. */
	@Email(message = ConstantsViews.ERRORSELECTMAIL, regexp = ConstantsViews.EMAIL_PATTERN)
	private String email;

	/** The message. */
	@NotNull(message = ConstantsViews.ERRORWRITEMESSAGE)
	@NotEmpty(message = ConstantsViews.ERRORWRITEMESSAGE)
	private String message;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}