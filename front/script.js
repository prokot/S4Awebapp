window.addEventListener('load', () => {
  const cargoForm = document.getElementById('cargoForm');

  cargoForm.addEventListener('submit', event => updateCargoAction(event));


  const IATAForm = document.getElementById('IATAForm');

  IATAForm.addEventListener('submit', event => updateIATAAction(event));
});


function updateIATAAction(event) {
  event.preventDefault();

  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      displayIATAFlight(JSON.parse(this.responseText))
    }
  };
  var iata = document.getElementById('IATACode').value;
  xhttp.open("GET", "http://127.0.0.1:8081" + "/api/flights" +
  "?departureDate=" + document.getElementById('departureDate1').value +"&IATACode="+
  iata
  , true);
  xhttp.send();

}

function updateCargoAction(event) {
  event.preventDefault();

  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
      displayCargoWeight(JSON.parse(this.responseText))
    }
  };
  xhttp.open("GET", "http://127.0.0.1:8081" + "/api/flights" +
  "?departureDate=" + document.getElementById('departureDate').value +"&flightNumber="+
  document.getElementById('flightNumber').value
  , true);
  xhttp.send();

}

function displayIATAFlight(response) {
  setTextNode('arrivNo', response.arriveNo);
  setTextNode('departNo', response.departNo);
  setTextNode('arrivPieces', response.arrivePieces);
  setTextNode('departPieces', response.departPieces);
}


function displayCargoWeight(response) {
  setTextNode('CargoWeight', response.cargoWeight);
  setTextNode('BaggageWeight', response.baggageWeight);
  setTextNode('TotalWeight', response.totalWeight);
}

function setTextNode(id, text) {
  let element = document.getElementById(id);
  clearElementChildren(element);
  element.appendChild(document.createTextNode(text));
}

function clearElementChildren(element) {
  while (element.firstChild) {
      element.removeChild(element.firstChild);
  }
}
