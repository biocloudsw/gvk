  $( "#search-param" ).autocomplete({
 	  classes: {
		"ui-autocomplete": "highlight"
	  },
      source: function( request, response ) {
        $.ajax({
          url: "/gvk/goajax/getSearchWord?time="+new Date().getTime(),
          dataType: "json",
          data: {
            featureClass: "P",
            style: "full",
            maxRows: 12,
            nameStartsWith: request.term,
			ontologyType : 1
          },
          success: function( data ) {
            response( $.map( data.wordList, function( item ) {
              return {
                label: item.name,
                value: item.name,
				id: item.name,
				type: item.type
              }
            }));
          }
        });
      },
      minLength: 3,
      select: function( event, ui ) {
        console.log( ui.item ?
          "Selected: " + ui.item.label :
          "Nothing selected, input was " + this.value);
		 // $("#idontolgoy").val(ui.item.label);
		  
      },
      open: function() {
        $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
      },
      close: function() {
        $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
      }
    });


  $.ui.autocomplete.prototype._renderItem = function (ul, item) {        
    var t = String(item.value).replace(
            new RegExp(this.term, "gi"),
            "<strong>$&</strong>");

	if(item.type == 1){
	 return $("<li></li>")
        .data("item.autocomplete", item)
        .append("<div>" + t + " (Trait) </div>")
        .appendTo(ul);
	}else if(item.type == 2){
	 return $("<li></li>")
        .data("item.autocomplete", item)
        .append("<div>" + t + " (Gene)</div>")
        .appendTo(ul);
	}
	else {
	 return $("<li></li>")
        .data("item.autocomplete", item)
        .append("<div>" + t + "</div>")
        .appendTo(ul);
	}
   
};