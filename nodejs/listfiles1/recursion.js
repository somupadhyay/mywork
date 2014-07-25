var fs = require('fs');
var markdown = require( "markdown" ).markdown;

exports.logInConsole = function(path){
	console.log("I was called..."+path);
}

exports.listFiles = function(path, req, res, dirCallback, fileCallback){
	// checking the file path and logging it.
	fs.realpath(path, function(err, outpath){
		if (err) {
			console.log(err);
			return;
		}
		//console.log("real path async : "+outpath);
	});

	//Reading the file path and listing the files or dirs in it.
	fs.stat(path, function(err, stat){
		if (err) {
			console.log("error occured while listing files : "+err);
			return;
		}
		var isDir =false;
		if (stat && stat.isDirectory()) {

			fs.readdir(path,function(err,contents){
				if (err) {
					console.log("error occured while reading directory : "+err);
					return;
				}
				dirCallback(contents, res);
				return;				
			});
			
		}else {
			fs.readFile(path, 'utf8', function (err, data) {
  				if (err) throw err;
  				if(path.indexOf(".md") > -1 || path.indexOf(".markdown")> -1 || path.indexOf(".MD")> -1 || path.indexOf(".MARKDOWN")> -1){
  					console.log("Markdown File "+path);
  					fileCallback(markdown.toHTML(data),res);
  					return;
  				}else{
  					console.log("Normal File "+path);
  					fileCallback(data, res);
  					return;
  				} 				
			});
		}

	});

}