
			
		
			
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
			var ref = window.setInterval(function(){getPosition()}, 500);
								
								
			
			function setDestinationPoint(lt, ln){				
				
				latLong = new google.maps.LatLng(lt,ln);				
				geocoder.geocode({'latLng': latLong}, function(results, status){					
			
					if(status == google.maps.GeocoderStatus.OK){						
												
					// configurando o endereço inicial.
					end = results[0].formatted_address;					
							
					//showRoute(start,end);
					calcRoute(lat, lng, lt, ln);
					
					}else{
									
						alert(status);
				
					}
			
				});			
				
				//configurando rota.
				//showRoute(start,end);
				
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
					
					//document.getElementById("locais").disabled = false;
					infoWindow.setContent(results[0].formatted_address);
					infoWindow.open(map, marcadorInicial);
					
					}else{
									
					alert(status);
				
					}
			
				});			
			
			
			}
			
			function calcRoute(lts,lns,lte,lne){
				directionsService = new google.maps.DirectionsService();
				directionsDisplay = new google.maps.DirectionsRenderer();
				
				var latLngStart = new google.maps.LatLng(lts,lns);
				var latLngEnd = new google.maps.LatLng(lte,lne);
				
				directionsDisplay.setMap(map);
				directionsDisplay.setPanel(document.getElementById("directionsPanel"));
				
				var request = {
						origin:latLngStart,
						destination:latLngEnd,
						travelMode: google.maps.TravelMode.DRIVING
						
				};
				
				//alert(lts+" "+lns);
				//alert(lte+" "+lne);
				
				directionsService.route(request, function(response, status){
					
					//alert(status);
					if(status == google.maps.DirectionsStatus.OK){
						
						//var bounds = google.maps.LatLngBounds(latLngStart, latLngEnd);
						//map.fitBounds(bounds);
						
						directionsDisplay.setDirections(response);
						directionsDisplay.setMap(map);
						
						
						
					}
					
				});
				
			}
			
			function showRoute(s,e){
			
			var request = {
				origin:s,
				destination:e,
				travelMode: google.maps.DirectionsTravelMode.DRIVING
			};
			
			alert(s+"       "+e);
			
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
				//alert(lat+" "+lng);
				
				jsfLat = document.getElementById("latLng:lat");
				jsfLng = document.getElementById("latLng:lng");
				//jsfLat.value = lat;
				//jsfLng.value = lng;
				//alert(jsfLat.value);
								
				map = primeMap.getMap();
								
				latLong = new google.maps.LatLng(lat,lng);				
				geocoder.geocode({'latLng': latLong}, function(results, status){			
			
					if(status == google.maps.GeocoderStatus.OK){						
						
					// configurando o endereço inicial.
					start = results[0].formatted_address;					
									
					}else{
									
						alert(status);
				
					}
			
				});	
				
			
				
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
			
						
			function enableInterface(){
			
				document.getElementById("findBtn").disabled = false;
				document.getElementById("destinationAddress").disabled = false;
				document.getElementById("addDestinoBtn").disabled = false;				
			
			}// fim da função enableInterface.
		
				