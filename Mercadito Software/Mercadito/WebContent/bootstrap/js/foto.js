/*
var varHidden2 = document.getElementById("#idHidden2");
							
						
							window.addEventListener('load', init);
							function init(){
								var video=document.querySelector('#v'), 
								canvas=document.querySelector('#e'),
							    startbutton  = document.querySelector('#botonIniciar'),
								btn=document.querySelector('#t');
								
								window.video = {
									    'StreamVideo': null
									}
								
								
								navigator.getUserMedia=(navigator.getUserMedia ||
										navigator.webkitGetUserMedia||
										navigator.mozGetUserMedia||
										navigator.msGetUserMedia);
								startbutton.addEventListener('click', function(ev){
								varHidden2.setAttribute('value', '');
									if(navigator.getUserMedia){
									navigator.getUserMedia({video:true},function(stream){
								        video.StreamVideo = stream;
										video.src=window.URL.createObjectURL(stream);
										video.play();
										
									},function(e){console.log(e);})
								}
								else alert('Tienes un navegador obsoleto');
								});
								video.addEventListener('loadedmetadata',function(){canvas.width=video.videoWidth;canvas.height=video.videoHeight;},false);
								
								btn.addEventListener('click', function(){
								        video.StreamVideo.stop();
									canvas.getContext('2d').drawImage(video,0,0);
									var imgData2=canvas.toDataURL('image/png');
								
									varHidden2.setAttribute('value', imgData2);
								});
							}
*/
var varHidden2 = document.getElementById("idForm:idHidden2");
								window.addEventListener('load', init);
								function init(){
									var video=document.querySelector('#v'), 
									canvas=document.querySelector('#e'),
								    startbutton  = document.querySelector('#botonIniciar'),
									btn=document.querySelector('#t');
									window.video = {
										    'StreamVideo': null
										}
									navigator.getUserMedia=(navigator.getUserMedia ||
											navigator.webkitGetUserMedia||
											navigator.mozGetUserMedia||
											navigator.msGetUserMedia);
									startbutton.addEventListener('click', function(ev){
									varHidden2.setAttribute('value', '');
										if(navigator.getUserMedia){
										navigator.getUserMedia({video:true},function(stream){
									        video.StreamVideo = stream;
											video.src=window.URL.createObjectURL(stream);
											video.play();
											
										},function(e){console.log(e);})
									}
									else alert('Tienes un navegador obsoleto');
									});
									video.addEventListener('loadedmetadata',function(){canvas.width=video.videoWidth;canvas.height=video.videoHeight;},false);
									
									btn.addEventListener('click', function(){
									        video.StreamVideo.stop();
										canvas.getContext('2d').drawImage(video,0,0);
										var imgData2=canvas.toDataURL('image/png');
									
										varHidden2.setAttribute('value', imgData2);
									});
								}