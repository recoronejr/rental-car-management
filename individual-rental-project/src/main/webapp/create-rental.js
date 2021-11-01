function addVehicleToForm(vehicle) {
            var option = document.createElement("option");
            var br = document.createElement("br");
            var vehicles = document.getElementById("select-vehicle");

            option.value = vehicle.year + " " + vehicle.make + " " + vehicle.model;
            option.innerHTML = vehicle.year + " " + vehicle.make + " " + vehicle.model;

            vehicles.appendChild(option);
            vehicles.appendChild(br);
          }
          document.getElementById("new-rental").addEventListener('submit', function(event) {
            event.preventDefault();

            var vehicle = document.getElementById('select-vehicle').value;		
            var pricePerDay = Number(document.getElementById('post_pricePerDay').value);	
            var mileage = parseInt(document.getElementById('post_mileage').value);
            var isAvailable = document.getElementById('post_isAvailable').value;

            var newRental = {
              vehicle: vehicle,
              pricePerDay: pricePerDay,
              mileage: mileage,
              isAvailable: isAvailable
            } 
            console.log(JSON.stringify(newRental));
            let xhrNewRental = new XMLHttpRequest();	

            xhrNewRental.onreadystatechange = function() {
    
              if(xhrNewRental.readyState === 4) {	

                  var updated = JSON.parse(xhrNewRental.responseText);
                  addRentalToTable(item);
                  document.getElementById('new-rental').reset();
                  // Hide Modal
                  $('#rentalModal').modal('hide');
                  
              }
            }

            xhrNewRental.open('POST', '/individual-rental-project/rental');
           
            //xhrNewRental.send(JSON.stringify(newRental));	
});