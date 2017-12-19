
//////////////////////////////////////////////////////////////////////////////////////////////////////
//Neon - Responsive Under Cunstruction Page
//////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////countdwn script//////////////////////////////////////////////////
// documentation of jCountdown plugin --> http://www.webmuse.co.uk/projects/jcountdown-jquery-plugin/
// here it's simple you have to just change your lunch date !!!
$(document).ready(function() {
    var date = new Date().toLocaleDateString();
    console.log(date);
	$("#count").countdown({
		//to change lunch date just replace the current date with yours .
		//date: "march 13, 2019",
		date: date,
		//html code in count div here.
		htmlTemplate: "<div id='sec-count' class='numbers'>%{s}<span class='sec-label'>sec</span></div>"
	});
});

////////////////////show-hide tools panel////////////////////////
$("#show-hide").live("click", function(){ 
	if ($('#tools').is(':visible'))
		{$(this).html('+');$("#tools").hide();}
	else{$(this).html('-');$("#tools").show();}
 }); 

////////////////////skins selectors change////////////////////////
$("#blue-skin").live("click", function(){ 
	$("#container").attr("class","blue");
 }); 

$("#red-skin").live("click", function(){ 
	$("#container").attr("class","red");
 }); 

$("#purple-skin").live("click", function(){ 
	$("#container").attr("class","purple");
 }); 

$("#green-skin").live("click", function(){ 
	$("#container").attr("class","green");
 }); 


////////////////////patterns selectors change////////////////////////
$("#pat1").live("click", function(){ 
	 $('html').attr("class","bg1");
 }); 

$("#pat2").live("click", function(){ 
	$('html').attr("class","bg2");
 }); 

$("#pat3").live("click", function(){ 
	$('html').attr("class","bg3");
 }); 

$("#pat4").live("click", function(){ 
	$('html').attr("class","bg4");
 }); 