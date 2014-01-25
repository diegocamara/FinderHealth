
		
			
			var map;
			var geocoder = new google.maps.Geocoder();
			var directionsDisplay = new google.maps.DirectionsRenderer();
			var directionsService = new google.maps.DirectionsService();
			var marcadorInicial, marcadorFinal;
			var infoWindow = new google.maps.InfoWindow();
			var localEnabled = false;
			var endereco = "Brasil";
			var start, end;
			var duracao;// duração do percurso.
			var distancia;// distancia do percurso.
			var latLong;			
			var lat;
			var lng;
			
		
			
						
			// executa getPosition em um intervalo de dois segundos.
			var ref = window.setInterval(function(){getPosition()}, 3000);
								
			
			
			function initialize(randZoom){				
			
								
				geocoder.geocode({'address': endereco},function(results, status){					
					if(status == google.maps.GeocoderStatus.OK){					
						latLong = results[0].geometry.location;	
												
						var mapOptions = {
						center: latLong,
						zoom: randZoom,
						mapTypeId: google.maps.MapTypeId.ROADMAP
						};
					
						map = new google.maps.Map(document.getElementById("map_canvas"),mapOptions);
						directionDisplay.setMap(map);
					}else{
					alert(status);
					}
					
				});
				
			}// fim da função initialize.
			
			
			function setDestinationPoint(){

				endereco = document.getElementById("locais").value;

				// configurando o endereço de destino.
				end = endereco;
				
				/*
				
				geocoder.geocode({'address': endereco},function(results,status){

					if(status == google.maps.GeocoderStatus.OK){
						
						latLong = results[0].geometry.location;

						marcadorFinal = new google.maps.Marker({
							position: latLong,
							map: map,							
						});
					showRoute(start,end);
					}else{

						alert("Testing");

					}

				});
				*/
				//configurando rota.
				showRoute(start,end);
				
			}// fim da função getDestinationPoint.

			
			function getPosition(){				
				
								
				if(navigator.geolocation && !localEnabled) {
				
					navigator.geolocation.getCurrentPosition(getCoords,showError);
					
				}else{
					// para de executar a função getPosition se o usuário ativar a localização.
					clearInterval(ref);
				}
				
			}// fim da função getPosition.
						
			
			function updatePoint(){
			
							
				latLong = new google.maps.LatLng(lat,lng);				
				geocoder.geocode({'latLng': latLong}, function(results, status){
			
					if(status == google.maps.GeocoderStatus.OK){						
						if(results[0]){
							map.setZoom(10);
							marcadorInicial = new google.maps.Marker({
								position: latLong,
								map: map
							});
						}
					// configurando o endereço inicial.
					start = results[0].formatted_address;
					document.getElementById("locais").disabled = false;
					infoWindow.setContent(results[0].formatted_address);
					infoWindow.open(map, marcadorInicial);
					
					}else{
									
					//alert(status);
				
					}
			
				});			
			
			
			}
			
			
			
			function showRoute(s,e){
			
			var request = {
				origin:s,
				destination:e,
				travelMode: google.maps.DirectionsTravelMode.DRIVING
			};
			
			directionsService.route(request, function(response, status){
			
				if (status == google.maps.DirectionsStatus.OK){
					directionsDisplay.setDirections(response);
					directionsDisplay.setMap(map);
					
					//tirando os marcadores da tela.
					marcadorInicial.setMap(null);
					marcadorFinal.setMap(null);
				}else if(status == google.maps.DirectionsStatus.ZERO_RESULTS){
					alert("Nenhum resultado encontrado para este endereço.");
				}
			
			
			});
			
			
			calculateDistances();
			
			}// fim da função showRoute.
			
			
					
			function calculateDistances(){
			
				// criando um objeto DistanceMatrixService.
				var service = new google.maps.DistanceMatrixService();
				//alert(start+" "+ end);
				service.getDistanceMatrix({
					origins:[start],
					destinations:[end],
					travelMode: google.maps.TravelMode.DRIVING,
					unitSystem: google.maps.UnitSystem.METRIC,
					avoidHighways: false,
					avoidTolls: false					
				},  callback);
			
			}// fim da função calculateDistances.
			
			
			function callback(response, status){
						
						if(status == google.maps.DistanceMatrixStatus.OK){
						
							var origem = response.originAddresses;
							var destino = response.destinationAddresses;							
							var resultados = response.rows[0].elements[0];
							duracao = resultados.duration.text;
							distancia = resultados.distance.text;
							
							// configura os campos.
							document.getElementById("origem").innerHTML = "";
							document.getElementById("destino").innerHTML = "";
							document.getElementById("distancia").innerHTML = "";
							document.getElementById("duracao").innerHTML = "";
							
							document.getElementById("origem").innerHTML += " " + origem;
							document.getElementById("destino").innerHTML += " " + destino;
							document.getElementById("distancia").innerHTML += " " + distancia;
							document.getElementById("duracao").innerHTML += " " + duracao;
							
						
							
							duracao = resultados.duration.value;
							distancia = resultados.distance.value;
							
						
							
						}else{
							alert('Error was: ' + status);
						}
						
			}// fim da função callback.			
		
			
			function getCoords(position){									  
				lat = position.coords.latitude;
				lng = position.coords.longitude;
				
				jsfLat = document.getElementById("topMenu:lat");
				jsfLng = document.getElementById("topMenu:lng");
				jsfLat.value = lat;
				jsfLng.value = lng;
				
				if(window.lat == undefined && window.lng == undefined){
					localEnabled = false;
				}else{
					localEnabled = true;	
					enableInterface();
				}
				
				//alert(lat + " " + lng);
			}// fim da função showPosition.
			
			function showError(error){
			
				switch(error.code){
				
						case error.PERMISSION_DENIED:
							alert("DENIED");
						break;
					
						case error.POSITION_UNAVALIABLE:
							alert("UNAVALIABLE");
						break;
					
						case error.TIMEOUT:
							alert("TIMEOUT");
						break;
					
						case error.UNKNOWN_ERROR:
							alert("UNKNOW_ERROR");
						break;			
				
							
				}// fim do switch.
			
			}// fim da função showError.
			
			function insertAddress(){
				
				var select = document.getElementById("locais");
				var option = document.createElement("option");
				option.text = document.getElementById("destinationAddress").value;
				option.value = option.text;
				
				try{
					
					select.add(option, select.options[null]);
					
				}catch(e){
					select.add(option, null);
				}
				
			}// fim da função insertAddress.
			
			function enableInterface(){
			
				document.getElementById("findBtn").disabled = false;
				document.getElementById("destinationAddress").disabled = false;
				document.getElementById("addDestinoBtn").disabled = false;				
			
			}// fim da função enableInterface.
		
			
			function calculaPrecoTaxi(){ //Função para calcular o valor da tarifa do taxi 
				var bandeira1 = 3.80; // valor da bandeira 1
				var bandeira2 = 4.00; // valor da bandeira 2
				var txBand1 = 1.85;   // taxa por km rodado para bandeira 1
				var txBand2 = 2.22;   // taxa por km rodado para bandeira 2
				var valorBandeira;    // valor da bandeira para o calculo do valor
				var kmRodado;         // distância do percurso
				var txKmRodado;       // taxa por km rodado
				var valorFinal;       // valor a ser pago para o taxi 
				var dia;             // variavel para pegar o dia atual do sistema
				var horas;			// variavel para pegar as horas atual do sistema	
				
				horas = new Date().getHours();  // hora atual do sistema
				
				dia = new Date().getDay();    // dia atual do sistema
				
				kmRodado = distancia/1000;                                         // distancia do percurso
				
				if(horas >= 22 || horas < 06 || dia == 0 || dia == 6){ // teste para saber se é bandeira 2
					valorBandeira = bandeira2;
					txKmRodado = txBand2
				}else{                                   // bandeira 1
					valorBandeira = bandeira1;
					txKmRodado = txBand1;
				}
				
				valorFinal = valorBandeira + (kmRodado * txKmRodado);
				
				document.getElementById("precoTaxi").innerHTML = valorFinal;
							}
			
			function calculaValorGasolina(){  // Função para calcular o gasto com a gasolina
				var consumoCarro;    // consumo de gasolina do carro
				var litros;          // quantidade de litros necessária para realizar o percurso   
				var precoGasolina=2.729;           // valor da gaolina
				var distancia_percursso;          // distancia total do percurso
				var precoFinal;                   // valor gasto com a gasolina
		
				consumoCarro = document.getElementById("inputConsumo").value;	  //pega o consumo digitado pelo usuário	
				alert(consumoCarro);
				distancia_percursso=distancia/1000;				// distancia do percurso		
				alert(distancia_percursso);
				litros = (distancia/1000)/consumoCarro; 				// calcula quantos litros serão gastos para relizar o percurso
				alert(litros);
				
				precoFinal = litros * precoGasolina;                // valor do gasto com a gasolina
				alert(precoFinal);
				document.getElementById("preco").innerHTML = precoFinal;
			}
