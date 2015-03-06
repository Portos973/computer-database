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
        	computerName: "required",
            introduced: {
                required: true,
                formatDate:true
            },
            agree: "required"
        },
        
        // Specify the validation error messages
        messages: {
        	computerName: "Please enter one name for this computer",
            introduced: {
                required: "Please provide a indroduced date",
                minlength: "Please respect format date yyyy/mm/dd"
            },
            agree: "Please accept our policy"
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });

  });
  
