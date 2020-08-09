// Part of SourceAFIS: https://sourceafis.machinezoo.com
package com.grace.biometricattendance.sourceafis;

import java.util.List;

import gnu.trove.map.hash.*;

class ImmutableMatcher {
	static final ImmutableMatcher empty = new ImmutableMatcher();
	final ImmutableTemplate template;
	final TIntObjectHashMap<List<IndexedEdge>> edgeHash;
	private ImmutableMatcher() {
		template = ImmutableTemplate.empty;
		edgeHash = new TIntObjectHashMap<>();
	}
	ImmutableMatcher(ImmutableTemplate template, TIntObjectHashMap<List<IndexedEdge>> edgeHash) {
		this.template = template;
		this.edgeHash = edgeHash;
	}
}
