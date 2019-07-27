jQuery(function ($) {

    $(".sidebar-dropdown > a").click(function() {
  $(".sidebar-submenu").slideUp(200);
  if (
    $(this)
      .parent()
      .hasClass("active")
  ) {
    $(".sidebar-dropdown").removeClass("active");
    $(this)
      .parent()
      .removeClass("active");
  } else {
    $(".sidebar-dropdown").removeClass("active");
    $(this)
      .next(".sidebar-submenu")
      .slideDown(200);
    $(this)
      .parent()
      .addClass("active");
  }
});

$("#close-sidebar").click(function() {
  $(".page-wrapper").removeClass("toggled");
});
$("#show-sidebar").click(function() {
  $(".page-wrapper").addClass("toggled");
});

});

//$(document).ready( function () {
//    $('#userlisttable').DataTable(
//            ajax: {
//                       url: 'http://localhost:8082/spring-security-oauth-resource/users',
//                       method: "GET",
//                       xhrFields: {
//                           withCredentials: true
//                       }
//                    },
//                    columns: [
//                        { data: 0 }
//                        /*and so on, keep adding data elements here for all your columns.*/
//                    ]
//                }
//    {
//    lengthChange: false,
//    searching: false,
//    ordering:  false
//    });
//} );