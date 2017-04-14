Face Detection with Open CV - Java
==================

[![Platform](https://img.shields.io/badge/platform-Mac%2FWindows-blue.svg
)](https://www.apple.com/macos/sierra/)
[![Language](https://img.shields.io/badge/library-opencv-green.svg)](http://opencv.org)
[![Language](https://img.shields.io/badge/language-javascript-orange.svg)](http://www.oracle.com/technetwork/java/index-138747.html)

This project helps you create a video with Face Detection from an array of images.  

<a href="https://imgflip.com/gif/1n85fw"><img src="https://i.imgflip.com/1n85fw.gif" title="made at imgflip.com"/></a>

### Requirements
- IDE: Eclipse
- Languague: Java
- OpenCV 3.2.0

## Instruction (Mac OSX)
### Install [Apache Ant](http://ant.apache.org/manual/install.html) first

### Download OpenCV
- [OpenCV 3.2 Package](https://sourceforge.net/projects/opencvlibrary/) 
### Build file OpenCV-329.jar 
- After downloading, extract the package.
- Open <b>Terminal</b>
- `$ cd opencv-2.4.7`
- `$ mkdir build`
- `$ cd build/`
- `$ cmake -G "Unix Makefiles" -D CMAKE_CXX_COMPILER=/usr/bin/g++ -D CMAKE_C_COMPILER=/usr/bin/gcc -D WITH_CUDA=ON ..` (3*)
- `$ make -j4 `
- `$ make install`

=> After processing done, it builds out `opencv-320.jar` file in directory `build/bin/`

- Add OpenCV into Eclipse: [(link)](http://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html)

### Make some changes in source code
- Replace string `"/Users/mac/Desktop/"` to a real directory where the`FaceDetection` folder is located.
- Run and get the video output in `FaceDetection` folder.

### Bonus
- UnComment the following lines of code to save the <b>images into disk with face detection</b> 
<br>` // String filename = "imageWithFaces/" + originalFileName;`
<br>`	// Imgcodecs.imwrite(filename, image);`


