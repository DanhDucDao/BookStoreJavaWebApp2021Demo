
// add event lisenter to input cover image
$('#coverImage').on('change', function(e) {readURL(e.target)});
var inputIamge = document.getElementById("coverImage");

//function to handle load image
function readURL(input) {
	var files = input.files;
	for(let x in files) {
		var filesize = (files[x].size/1024/1024).toFixed(4);
		if(filesize > 25) {
			alert("File too large, must be under 25MB");
			input.value = "";
			return;
		}
	}
	
	if(input.files && input.files[0]) {
		var reader = new FileReader();
		console.log(input.files);
		reader.onload = function(e) {
			$('#loadImage').attr('src', e.target.result).width(150).height(200);
		};

		reader.readAsDataURL(input.files[0]);
	}
}