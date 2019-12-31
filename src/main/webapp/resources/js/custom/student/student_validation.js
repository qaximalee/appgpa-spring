var isValidated = true;
	
		function fnameValidation(value){
			var regName = /^[a-zA-Z]+$/;
			var name = value;
			document.getElementById('fname_error').innerHTML = '';
			if(!regName.test(name)){
				document.getElementById('fname_error').innerHTML = 'Enter Correct First Name';
				isValidated = false;
			}else if(name == ""){
				document.getElementById('fname_error').innerHTML = 'First Name should be filled';
				isValidated = false;
			}
		}
		
		function lnameValidation(value){
			var regName = /^[a-zA-Z]+$/;
			var name = value;
			document.getElementById('lname_error').innerHTML = '';
			if(!regName.test(name)){
				document.getElementById('lname_error').innerHTML = 'Enter Correct Last Name';
				isValidated = false;
			}else if(name == ""){
				document.getElementById('lname_error').innerHTML = 'Last Name should be filled';
				isValidated = false;
			}
		}
		
		function isRegNoValid() {
			var value = document.getElementById("registrationNo").value;
			var ins = value.search(/^EP-\d{7}$/);
			document.getElementById('reg_no_error').innerHTML = '';
			if (ins != 0) {
				document.getElementById('reg_no_error').innerHTML = 'Enter Correct Registration No (i.e EP-1234567)';
				isValidated = false;
				return false; 
			}else if(value == ""){
				document.getElementById('reg_no_error').innerHTML = 'Registration No should be filled';
				isValidated = false;
				return false;
			}
			isValidated = true;
			return true;
		}
		
		function getValidated(){
			return isValidated;
		}