// Part of SourceAFIS: https://sourceafis.machinezoo.com
package com.grace.biometricattendance.sourceafis;

class JsonEdge {
	int probeFrom;
	int probeTo;
	int candidateFrom;
	int candidateTo;
	JsonEdge(MinutiaPair pair) {
		probeFrom = pair.probeRef;
		probeTo = pair.probe;
		candidateFrom = pair.candidateRef;
		candidateTo = pair.candidate;
	}
}
