package org.cysoft.urbanbot.core.model;

import java.util.Arrays;
import java.util.List;

import org.cysoft.bss.core.model.ICyBssConst;

public class Keyboard {
	public static final String SELECTION_S="/s";
	public static final String SELECTION_N="/n";
	public static final String SELECTION_T="/t";
	public static final String SELECTION_E="/e";
	public static final String SELECTION_M="/m";
	public static final String SELECTION_L="/l";
	public static final String SELECTION_V="/v";
	public static final String SELECTION_B="/b";
	public static final String SELECTION_R="/r";
	public static final String SELECTION_P="/p";
	public static final String SELECTION_1="1";
	public static final String SELECTION_2="2";
	public static final String SELECTION_3="3";
	public static final String SELECTION_4="4";
	public static final String SELECTION_D="/d";
	public static final String SELECTION_START="/start";
	
	public static final String BUTTON_WARNING="Segnalazioni";
	public static final String BUTTON_STORY="Storie";
	public static final String BUTTON_EVENT="Eventi";
	public static final String BUTTON_SITE="Siti Turistici";
	public static final String BUTTON_MAP="Mappe";
	public static final String BUTTON_CHANGE_LANGUAGE="Change Language";
	public static final String BUTTON_NEW="Nuova";
	public static final String BUTTON_SHOW="Visualizza";
	public static final String BUTTON_SEARCH="Ricerca";
	public static final String BUTTON_BACK="Indietro";
	public static final String BUTTON_NEXT="Successivi";
	public static final String BUTTON_PREV="Precedenti";
	
	public static final String BUTTON_WARNING_EN="Warnings";
	public static final String BUTTON_STORY_EN="Stories";
	public static final String BUTTON_EVENT_EN="Events";
	public static final String BUTTON_SITE_EN="Tourist Sites";
	public static final String BUTTON_MAP_EN="Maps";
	public static final String BUTTON_CHANGE_LANGUAGE_EN="Cambia Lingua";
	public static final String BUTTON_NEW_EN="New";
	public static final String BUTTON_SHOW_EN="Show";
	public static final String BUTTON_SEARCH_EN="Search";
	public static final String BUTTON_BACK_EN="Back";
	public static final String BUTTON_NEXT_EN="Next";
	public static final String BUTTON_PREV_EN="Previous";
	
	public static List<List<String>> getWelcome(String language){
		if (language.equalsIgnoreCase(ICyBssConst.LOCALE_IT))
			return Arrays.asList(Arrays.asList(BUTTON_WARNING, BUTTON_STORY, BUTTON_SITE),
					Arrays.asList(BUTTON_EVENT,BUTTON_MAP, BUTTON_CHANGE_LANGUAGE));
		else
			return Arrays.asList(Arrays.asList(BUTTON_WARNING_EN, BUTTON_STORY_EN, BUTTON_SITE_EN),
					Arrays.asList(BUTTON_EVENT_EN,BUTTON_MAP_EN, BUTTON_CHANGE_LANGUAGE_EN));
	}
	
	public static List<List<String>> getNrvb(String language){
		if (language.equalsIgnoreCase(ICyBssConst.LOCALE_IT))
			return Arrays.asList(Arrays.asList(BUTTON_NEW, BUTTON_SEARCH),
					Arrays.asList(BUTTON_SHOW,BUTTON_BACK));
		else
			return Arrays.asList(Arrays.asList(BUTTON_NEW_EN, BUTTON_SEARCH_EN),
					Arrays.asList(BUTTON_SHOW_EN,BUTTON_BACK_EN));
	}
	
	public static List<List<String>> getB(String language){
		if (language.equalsIgnoreCase(ICyBssConst.LOCALE_IT))
			return Arrays.asList(Arrays.asList(BUTTON_BACK));
		else
			return Arrays.asList(Arrays.asList(BUTTON_BACK_EN));
	}
	
	public static List<List<String>> getNb(String language){
		if (language.equalsIgnoreCase(ICyBssConst.LOCALE_IT))
			return Arrays.asList(Arrays.asList(BUTTON_NEXT,BUTTON_BACK));
		else
			return Arrays.asList(Arrays.asList(BUTTON_NEXT_EN,BUTTON_BACK_EN));
	}
	
	public static List<List<String>> getPb(String language){
		if (language.equalsIgnoreCase(ICyBssConst.LOCALE_IT))
			return Arrays.asList(Arrays.asList(BUTTON_PREV,BUTTON_BACK));
		else
			return Arrays.asList(Arrays.asList(BUTTON_PREV_EN,BUTTON_BACK_EN));
	}
	
	public static List<List<String>> getNpb(String language){
		if (language.equalsIgnoreCase(ICyBssConst.LOCALE_IT))
			return Arrays.asList(Arrays.asList(BUTTON_NEXT,BUTTON_PREV,BUTTON_BACK));
		else
			return Arrays.asList(Arrays.asList(BUTTON_NEXT_EN,BUTTON_PREV_EN,BUTTON_BACK_EN));
	}
	
	
	public static List<List<String>> get14b(String language){
		if (language.equalsIgnoreCase(ICyBssConst.LOCALE_IT))
			return Arrays.asList(Arrays.asList("1", "2", "3","4"),Arrays.asList(BUTTON_BACK));
		else
			return Arrays.asList(Arrays.asList("1", "2", "3","4"),Arrays.asList(BUTTON_BACK_EN));
	}
	
	public static List<List<String>> getStart(){
			return Arrays.asList(Arrays.asList(SELECTION_START));
		
	}
}
