/*
							var varHidden = document.getElementById("idForm:idHidden");
						
							window.addEventListener('load', init);
							function init(){
								var canvas=document.querySelector('#d'),
								context,painting;
								
								if (canvas == null) return;
							      
							      context = canvas.getContext("2d");
							      if (context == null) return;
							   
							      painting = false;
							      
							      context.strokeStyle = "#00f";
							      context.lineWidth = 3;
							      context.font = "15px Helvetica";
								
								canvas.addEventListener("mousedown", function(ev) {
								      painting = true;
								      context.beginPath();
								      context.moveTo(ev.offsetX, ev.offsetY);
								   }, false);
								   
								   canvas.addEventListener("mousemove", function(ev) {
								      updateReadout(ev.offsetX, ev.offsetY);

								      if (painting) {
								        paint(ev.offsetX, ev.offsetY);
								      }
								      function updateReadout(x, y) {
								         context.clearRect(0, 0, 0, 0);
								      }
								      function paint(x, y) {
								         context.lineTo(ev.offsetX, ev.offsetY);
								         context.stroke();
								      }
								         
								   }, false);
								   
								   canvas.addEventListener("mouseup", function() {
								      painting = false;
								      context.closePath();
								   }, false);
								
								canvas.addEventListener("mousedown", function(ev) {	//mousemove - antes
									var imgData=canvas.toDataURL('image/png');
								
									varHidden.setAttribute('value', imgData);
								});
							}
*/


							var varHidden = document.getElementById("#idForm:idHidden");
						
							window.addEventListener('load', init);
							function init(){
								var canvas=document.querySelector('#d'),
								context,painting;
								
								if (canvas == null) return;
							      
							      context = canvas.getContext("2d");
							      if (context == null) return;
							   
							      painting = false;
							      
							      context.strokeStyle = "#00f";
							      context.lineWidth = 3;
							      context.font = "15px Helvetica";
								
								canvas.addEventListener("mousedown", function(ev) {
								      painting = true;
								      context.beginPath();
								      context.moveTo(ev.offsetX, ev.offsetY);
								   }, false);
								   
								   canvas.addEventListener("mousemove", function(ev) {
								      updateReadout(ev.offsetX, ev.offsetY);

								      if (painting) {
								        paint(ev.offsetX, ev.offsetY);
								      }
								      function updateReadout(x, y) {
								         context.clearRect(0, 0, 0, 0);
								      }
								      function paint(x, y) {
								         context.lineTo(ev.offsetX, ev.offsetY);
								         context.stroke();
								      }
								         
								   }, false);
								   
								   canvas.addEventListener("mouseup", function() {
								      painting = false;
								      context.closePath();
								   }, false);
								
								canvas.addEventListener("mousemove", function(ev) {	
									var imgData=canvas.toDataURL('image/png');
								
									varHidden.setAttribute('value', imgData);
								});
							}
