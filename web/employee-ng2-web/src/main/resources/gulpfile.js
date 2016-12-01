var gulp = require('gulp');
var path = require('path');
var sourcemaps = require('gulp-sourcemaps');
var ts = require('gulp-typescript');
var del = require('del');
var concat = require('gulp-concat')
var runSequence = require('run-sequence');

// SERVER
gulp.task('clean', function(){
    return del('static')
});


// CLIENT
/*
  jsNPMDependencies, sometimes order matters here! so be careful!
*/
var jsNPMDependencies = [
    'angular2/bundles/angular2-polyfills.js',
    'systemjs/dist/system.src.js',
    'rxjs/bundles/Rx.js',
    'angular2/bundles/angular2.dev.js',
    'angular2/bundles/router.dev.js',
    "angular2/bundles/http.dev.js"
] 

gulp.task('build:index', function(){
    // var mappedPaths = jsNPMDependencies.map(file => {return path.resolve('node_modules', file)}) 
    var mappedPaths = jsNPMDependencies.map(function(file) {
        return path.resolve('node_modules', file)
    }) 
    
    //Let's copy our head dependencies into a dist/libs
    var copyJsNPMDependencies = gulp.src(mappedPaths, {base:'node_modules'})
        .pipe(gulp.dest('static/libs'))
     
    //Let's copy our index into dist   
    var copyIndex = gulp.src('ui/**/*.html')
        .pipe(gulp.dest('static'))
     var copyCSS = gulp.src('ui/**/*.css')
        .pipe(gulp.dest('static'))
    return [copyJsNPMDependencies, copyIndex, copyCSS];
});

gulp.task('build:app', function(){
    var tsProject = ts.createProject('ui/tsconfig.json');
    var tsResult = gulp.src('ui/**/*.ts')
		.pipe(sourcemaps.init())
        .pipe(ts(tsProject))
	return tsResult.js
        .pipe(sourcemaps.write()) 
		.pipe(gulp.dest('static'))
});

gulp.task('build', function(callback){
    runSequence('clean', 'build:index',  'build:app', callback);
});

gulp.task('default', ['build']);