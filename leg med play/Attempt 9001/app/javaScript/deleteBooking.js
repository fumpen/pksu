/**
 * Created by jeppe on 05-05-15.
 */

$(document).ready(function() {

    $("body").on("click", ".deleteBooking", function() {
        var listItem = this
        $.get(jsRoutes.controllers.Application.deleteBooking(listItem.id).url, {},
            function(data) {
                $(listItem).parent().remove();
            });
    });

    $("body").on("mouseover", ".deleteItem", function() {
        $(this).css("font-weight","bold");
    });
    $("body").on("mouseout", ".deleteItem", function() {
        $(this).css("font-weight","normal");
    });

    $("#myFormSubmit").click(function(){
        var name = $("#name").val();
        var lname = $("#lname").val();
        var arrdate = $("#from").val();
        var depdate = $("#to").val();
        var room = $("#room").val();
        $.getJSON(jsRoutes.controllers.Application.addBooking(name, lname, arrdate, depdate, room).url, {},
            function(data) {
                var listItem = $("<tr>"
                                    + "<td>" + data.name + "</td>"
                                    + "<td>" + data.lname + "</td>"
                                    + "<td>" + data.arrdate + "</td>"
                                    + "<td>" + data.depdate + "</td>"
                                    + "<td>" + data.room + "</td>"
                                    + "<td>" + "<a class=\"deleteBooking\" href=\"#\" id=\"" + data.id + "\">X</a>" + "</td>" +
                                "</tr>");
                $("#myList").append(listItem);
            });
    });

});