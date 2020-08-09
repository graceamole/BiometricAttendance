// Part of SourceAFIS: https://sourceafis.machinezoo.com
package com.grace.biometricattendance.sourceafis;

enum SkeletonType {
	RIDGES("ridges-"), VALLEYS("valleys-");
	final String prefix;
	SkeletonType(String prefix) {
		this.prefix = prefix;
	}
}
