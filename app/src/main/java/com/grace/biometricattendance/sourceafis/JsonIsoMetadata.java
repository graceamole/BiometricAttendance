// Part of SourceAFIS: https://sourceafis.machinezoo.com
package com.grace.biometricattendance.sourceafis;

class JsonIsoMetadata {
	int width;
	int height;
	int xPixelsPerCM;
	int yPixelsPerCM;
	JsonIsoMetadata(int width, int height, int xPixelsPerCM, int yPixelsPerCM) {
		this.width = width;
		this.height = height;
		this.xPixelsPerCM = xPixelsPerCM;
		this.yPixelsPerCM = yPixelsPerCM;
	}
}
