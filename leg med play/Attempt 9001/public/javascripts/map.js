$(document).ready(function() {

  		function initialize() {
  		        var mapCanvas = document.getElementById('map-canvas');
                var myLatlng = new google.maps.LatLng(11.125439, -63.866439);
                var mapOptions = {
                  center: myLatlng,
                  zoom: 13,
                  mapTypeId: google.maps.MapTypeId.ROADMAP
                }
                var map = new google.maps.Map(mapCanvas, mapOptions);
                var marker = new google.maps.Marker({
              		position: myLatlng,
              		map: map,
              		title: 'Organic Donkey'
          		});
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    });