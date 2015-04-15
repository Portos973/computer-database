/**
 * 
 */

$.validator.addMethod(
    "formatDate",
    function(value, element) {
        // put your own logic here, this is just a (crappy) example
        return value.match("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])");
    },
    "Please enter a date in the format yyyy/mm/dd."
);
  
  // When the browser is ready...
  $(function() {
  
    // Setup form validation on the #addComputer element
    $("#addComputer").validate({
    
        // Specify the validation rules
        rules: {
        	name: "required",
            agree: "required"
        },
        
        // Specify the validation error messages
        messages: {
        	name: "Please enter one name for this computer",
            agree: "Please accept our policy"
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });

  });
  
