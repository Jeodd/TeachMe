/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var mymap = L.map('openStreetMap').setView([48.5734, 7.7521], 5);
            L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
                attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
                maxZoom: 15,
                id: 'mapbox.streets',
                accessToken: localStorage.getItem('leaflet_api_key')
}).addTo(mymap);

var defaultIcon = L.icon({
    iconUrl: 'images/leaflet/marker-icon.png',

    iconSize: [25, 41], 
});

$.getJSON(localStorage.getItem('url_api') + 'locations/list', function(locations) {
    locations.forEach(function(location){
       L.marker([location['latitude'], location['longitude']], {icon: defaultIcon}).addTo(mymap).bindPopup("<b>" + location['city'] + "</b><br>Click <a href='index.jsp?location=" + location['id'] + "'>here</a> to see all courses available.");
    });
});