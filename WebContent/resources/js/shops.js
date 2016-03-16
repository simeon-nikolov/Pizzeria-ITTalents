jQuery(function($) {
    // Asynchronously Load the map API 
    var script = document.createElement('script');
    script.src = "http://maps.googleapis.com/maps/api/js?language=bg&callback=initialize";
    document.body.appendChild(script);
});

function initialize() {
    var map;
    var bounds = new google.maps.LatLngBounds();
    var mapOptions = {
        mapTypeId: 'roadmap'
    };
                    
    // Display a map on the page
    map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
    map.setTilt(45);
        
    // Multiple Markers
    var markers = [
        ['София - Борово', 42.673088565371444, 23.285871188360602],
        ['София - Гео Милев', 42.680476, 23.35668],
        ['София - Красна Поляна', 42.6956823, 23.2841548],
        ['София - Люлин', 42.711457, 23.246931],
        ['София - Младост', 42.636315, 23.369848],
        ['София - Студентски Град', 42.653846, 23.345557],
        ['София - Център', 42.697224, 23.317076],
        ['София - Южен парк', 42.674683, 23.308798]
    ];
                        
    // Info Window Content
    var infoWindowContent = [
        ['<div class="info_content">' +
        '<h3>София - Борово</h3>' +
        '<p>ул. Ген. Стефан Тошев 8</p>' + 
		'</div>'],
        ['<div class="info_content">' +
        '<h3>София - Гео Милев</h3>' +
        '<p>ул. Александър Жендов 6</p>' +
        '</div>'],
		['<div class="info_content">' +
        '<h3>София - Красна Поляна</h3>' +
        '<p>ул. Пчиня 31</p>' + 
		'</div>'],
        ['<div class="info_content">' +
        '<h3>София - Люлин</h3>' +
        '<p>ул. Панчо Владигеров 21</p>' +
        '</div>'],
		['<div class="info_content">' +
        '<h3>София - Младост</h3>' +
        '<p>бул. Александър Малинов 78</p>' + 
		'</div>'],
        ['<div class="info_content">' +
        '<h3>София - Студентски Град</h3>' +
        '<p>ул. Йордан Йосифов 4</p>' +
        '</div>'],
		['<div class="info_content">' +
        '<h3>София - Център</h3>' +
        '<p>ул. Александър Стамболийски 41</p>' + 
		'</div>'],
        ['<div class="info_content">' +
        '<h3>София - Южен парк</h3>' +
        '<p>ул. Бяла черква 1</p>' +
        '</div>']
    ];
        
    // Display multiple markers on a map
    var infoWindow = new google.maps.InfoWindow(), marker, i;
    
    // Loop through our array of markers & place each one on the map  
    for( i = 0; i < markers.length; i++ ) {
        var position = new google.maps.LatLng(markers[i][1], markers[i][2]);
        bounds.extend(position);
        marker = new google.maps.Marker({
            position: position,
            map: map,
            title: markers[i][0]
        });
        
        // Allow each marker to have an info window    
        google.maps.event.addListener(marker, 'click', (function(marker, i) {
            return function() {
                infoWindow.setContent(infoWindowContent[i][0]);
                infoWindow.open(map, marker);
            }
        })(marker, i));

        // Automatically center the map fitting all markers on the screen
        map.fitBounds(bounds);
    }

    // Override our map zoom level once our fitBounds function runs (Make sure it only runs once)
    var boundsListener = google.maps.event.addListener((map), 'bounds_changed', function(event) {
        this.setZoom(12);
        google.maps.event.removeListener(boundsListener);
    });
    
}