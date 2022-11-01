function Validator(options) {
	var formElement = document.querySelector(options.form);
	
	function validate(inputElement, rule) {
		var errorElement = inputElement.parentElement.querySelector(".form-msg");
		var errorMsg = rule.test(inputElement.value);
		if(errorMsg) {
			errorElement.innerText = errorMsg;
		} else {
			errorElement.innerText = "";
		}
	}
	
	if(formElement) {
		options.rules.forEach(function (rule) {
			var inputElement = formElement.querySelector(rule.selector);
			if(inputElement) {
				inputElement.onblur = function () {
					validate(inputElement, rule);
				}
				
				inputElement.oninput = function() {
					var errorElement = inputElement.parentElement.querySelector(".form-msg");
					errorElement.innerText = "";
				}
			}
		})
	}
};

Validator.isRequired = function (selector) {
	return {
		selector: selector,
		test: function(value) {
			return value.trim() ? undefined : "Vui long nhap truong nay!";
		}
	};
};

Validator.isEmail = function (selector) {
	return {
		selector: selector,
		test: function(value) {
			let regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			return regex.test(value) ? undefined : "Vui long nhap dung dinh dang email!";
		}
	};
};

Validator.minLength = function (selector, min) {
	return {
		selector: selector,
		test: function(value) {
			return value.length >= min ? undefined : `Mat khau phai bao gom it nhat ${min} ky tu!`;
		}
	};
};

Validator.isCOnfirmed = function(selector, getConfirmValue, errorMsg) {
	return {
		selector: selector,
		test: function(value) {
			return value === getConfirmValue() ? undefined : errorMsg;
		}
	};
}

Validator({
	form: "#register-form",
	rules: [
		Validator.isRequired("#name"),
		Validator.isEmail("#email"),
		Validator.minLength("#pass", 6),
		Validator.isCOnfirmed("#re_pass", function() {
			return document.querySelector("#register-form #pass").value;
		}, "Mat khau nhap lai khong chinh xac!")
	]
});