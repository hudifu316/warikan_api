package com.cosa2.warikan_api.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserBillControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void normal_success1() throws Exception {
		String input = "{\n" + "  \"activityName\": \"'🍣'='🍺'\",\n" + "  \"billingAmount\": 1000,\n"
				+ "  \"activityDate\": \"2020-10-04T15:32:32.572Z\",\n" + "  \"users\": [\n" + "    {\n"
				+ "   \"username\": \"abcdefghijklmnopqrst\",\n" + "      \"kanji\": true\n" + "    }\n" + "  ]\n" + "}";

		mockMvc.perform(post("/warikan").contentType(MediaType.APPLICATION_JSON).content(input))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.activityName").value("'🍣'='🍺'"))
				.andExpect(jsonPath("$.billingAmount").value(1000))
				.andExpect(jsonPath("$.users[0].username").value("abcdefghijklmnopqrst"))
				.andExpect(jsonPath("$.users[0].kanji").value(true))
				.andExpect(jsonPath("$.users[0].payAmount").value(1000));

	}

	@Test
	void normal_success2() throws Exception {
		String input = "{\n"
				+ "  \"activityName\": \"―ソЫⅨ噂浬欺圭構蚕十申曾箪貼能表暴予禄兔喀媾彌拿杤歃濬畚秉綵臀藹觸軆鐔饅鷭纊犾偆砡|－ポл榎掛弓芸鋼旨楯酢掃竹倒培怖翻慾處嘶斈忿掟桍毫烟痞窩縹艚蛞諫轎閖驂黥僴礰埈蒴@　ァА①院魁機掘后察宗拭繊叩邸如鼻法諭蓮僉咫奸廖戞曄檗漾瓠磧紂隋蕁襦蹇錙顱鵝ⅰ涖髜纊犾[ーゼЪⅧ閏骸擬啓梗纂充深措端甜納票房夕麓兌喙媼彈拏杣歇濕畆禺綣膽藜觴躰鐚饉鷦℡犱倞劯]‐ゾЬⅩ云馨犠珪江讃従疹曽綻転脳評望余肋兢咯嫋彎拆枉歉濔畩秕緇臂蘊訃躱鐓饐鷯褜猤偰硎^／タЭ運蛙疑型洪賛戎真楚耽顛膿豹某与録竸喊嫂弯擔杰歐濘畤秧綽膺蘓訖躾鐃饋\",\n"
				+ "  \"billingAmount\": 0,\n"
				+ "  \"activityDate\": \"20201007\",\n"
				+ "  \"users\": [\n"
				+ "    {\n"
				+ "      \"username\": \"uvwxyzABCDEFGHIJKLMN\",\n"
				+ "      \"kanji\": false\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"username\": \"OPQRSTUVWXYZ,.@-;:!#\",\n"
				+ "      \"kanji\": true\n"
				+ "    }\n"
				+ "  ]\n"
				+ "}";

		mockMvc.perform(post("/warikan").contentType(MediaType.APPLICATION_JSON).content(input))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.activityName").value("―ソЫⅨ噂浬欺圭構蚕十申曾箪貼能表暴予禄兔喀媾彌拿杤歃濬畚秉綵臀藹觸軆鐔饅鷭纊犾偆砡|－ポл榎掛弓芸鋼旨楯酢掃竹倒培怖翻慾處嘶斈忿掟桍毫烟痞窩縹艚蛞諫轎閖驂黥僴礰埈蒴@　ァА①院魁機掘后察宗拭繊叩邸如鼻法諭蓮僉咫奸廖戞曄檗漾瓠磧紂隋蕁襦蹇錙顱鵝ⅰ涖髜纊犾[ーゼЪⅧ閏骸擬啓梗纂充深措端甜納票房夕麓兌喙媼彈拏杣歇濕畆禺綣膽藜觴躰鐚饉鷦℡犱倞劯]‐ゾЬⅩ云馨犠珪江讃従疹曽綻転脳評望余肋兢咯嫋彎拆枉歉濔畩秕緇臂蘊訃躱鐓饐鷯褜猤偰硎^／タЭ運蛙疑型洪賛戎真楚耽顛膿豹某与録竸喊嫂弯擔杰歐濘畤秧綽膺蘓訖躾鐃饋"))
				.andExpect(jsonPath("$.billingAmount").value(0))
				.andExpect(jsonPath("$.users[0].username").value("uvwxyzABCDEFGHIJKLMN"))
				.andExpect(jsonPath("$.users[0].kanji").value(false))
				.andExpect(jsonPath("$.users[0].payAmount").value(0))
				.andExpect(jsonPath("$.users[1].username").value("OPQRSTUVWXYZ,.@-;:!#"))
				.andExpect(jsonPath("$.users[1].kanji").value(true))
				.andExpect(jsonPath("$.users[1].payAmount").value(0));

	}

	@Test
	void normal_success7() throws Exception {
		String input = "{\n"
				+ "  \"activityName\": \"鷽鍈猪偂硤_＼ダЮ㍉雲垣祇契浩酸柔神狙胆点農廟棒誉論兩喟媽彑拈枩歙濱畧秬綫臉蘋訐軅鐇饑鸚銈獷傔硺`～ＡチЯ㌔荏柿義形港餐汁秦疏蛋伝覗描冒輿倭兪啻嫣彖拜杼歔濮畫秡總臍藾訌軈鐐饒鸛蓜玽僴礰{＋ボк閲顎宮鶏砿施旬須捜畜怒倍府本養几嘴學悳掉桀毬炮痣窖縵艝蛔諚轆閔驅黠傔硺垬葈}±マм厭笠急迎閤枝殉図挿筑党媒扶凡抑凩嘲孺怡掵栲毳烋痾竈繃艟蛩諳轗閘驀黨僘礼埇蕓~×ミн㍻円樫救鯨降止淳厨掻蓄冬梅敷盆欲凭嘸宀恠捫桎毯烝痿窰縷艤蛬諧轜閙驃黯兊神﨏蕙\",\n"
				+ "  \"billingAmount\": 2147483647,\n"
				+ "  \"activityDate\": \"20201007\",\n"
				+ "  \"users\": [\n"
				+ "    {\n"
				+ "      \"username\": \"あいうえおかきくけこさしすせそたちつてと\",\n"
				+ "      \"kanji\": false\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"username\": \"なにぬねのはひふへほまみむめもやゐゆゑよ\",\n"
				+ "      \"kanji\": false\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"username\": \"らりるれろわをんっゃゅょゎヵヶッャュョヮ\",\n"
				+ "      \"kanji\": false\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"username\": \"1234567890１２３４５６７８９０\",\n"
				+ "      \"kanji\": false\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"username\": \"アイウエオカキクケコサシスセソタチツテト\",\n"
				+ "      \"kanji\": true\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"username\": \"ナニヌネノハヒフヘホマミムメモヤユヨワヲ\",\n"
				+ "      \"kanji\": false\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"username\": \"ラリルレロヰヱｹﾞｰﾑﾊﾟｹｯﾄがざだば\",\n"
				+ "      \"kanji\": false\n"
				+ "    }\n"
				+ "  ]\n"
				+ "}";

		mockMvc.perform(post("/warikan").contentType(MediaType.APPLICATION_JSON).content(input))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.activityName").value("鷽鍈猪偂硤_＼ダЮ㍉雲垣祇契浩酸柔神狙胆点農廟棒誉論兩喟媽彑拈枩歙濱畧秬綫臉蘋訐軅鐇饑鸚銈獷傔硺`～ＡチЯ㌔荏柿義形港餐汁秦疏蛋伝覗描冒輿倭兪啻嫣彖拜杼歔濮畫秡總臍藾訌軈鐐饒鸛蓜玽僴礰{＋ボк閲顎宮鶏砿施旬須捜畜怒倍府本養几嘴學悳掉桀毬炮痣窖縵艝蛔諚轆閔驅黠傔硺垬葈}±マм厭笠急迎閤枝殉図挿筑党媒扶凡抑凩嘲孺怡掵栲毳烋痾竈繃艟蛩諳轗閘驀黨僘礼埇蕓~×ミн㍻円樫救鯨降止淳厨掻蓄冬梅敷盆欲凭嘸宀恠捫桎毯烝痿窰縷艤蛬諧轜閙驃黯兊神﨏蕙"))
				.andExpect(jsonPath("$.billingAmount").value(2147483647))
				.andExpect(jsonPath("$.users[0].username").value("あいうえおかきくけこさしすせそたちつてと"))
				.andExpect(jsonPath("$.users[0].kanji").value(false))
				.andExpect(jsonPath("$.users[0].payAmount").value(306783378))
				.andExpect(jsonPath("$.users[1].username").value("なにぬねのはひふへほまみむめもやゐゆゑよ"))
				.andExpect(jsonPath("$.users[1].kanji").value(false))
				.andExpect(jsonPath("$.users[1].payAmount").value(306783378))
				.andExpect(jsonPath("$.users[2].username").value("らりるれろわをんっゃゅょゎヵヶッャュョヮ"))
				.andExpect(jsonPath("$.users[2].kanji").value(false))
				.andExpect(jsonPath("$.users[2].payAmount").value(306783378))
				.andExpect(jsonPath("$.users[3].username").value("1234567890１２３４５６７８９０"))
				.andExpect(jsonPath("$.users[3].kanji").value(false))
				.andExpect(jsonPath("$.users[3].payAmount").value(306783378))
				.andExpect(jsonPath("$.users[4].username").value("アイウエオカキクケコサシスセソタチツテト"))
				.andExpect(jsonPath("$.users[4].kanji").value(true))
				.andExpect(jsonPath("$.users[4].payAmount").value(306783379))
				.andExpect(jsonPath("$.users[5].username").value("ナニヌネノハヒフヘホマミムメモヤユヨワヲ"))
				.andExpect(jsonPath("$.users[5].kanji").value(false))
				.andExpect(jsonPath("$.users[5].payAmount").value(306783378))
				.andExpect(jsonPath("$.users[6].username").value("ラリルレロヰヱｹﾞｰﾑﾊﾟｹｯﾄがざだば"))
				.andExpect(jsonPath("$.users[6].kanji").value(false))
				.andExpect(jsonPath("$.users[6].payAmount").value(306783378));
	}

	@Test
	void tankoumoku_activity() throws Exception {
		String input = "{\n" + "  \"activityName\": \"12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901\",\n" + "  \"billingAmount\": 1000,\n"
				+ "  \"activityDate\": \"2020-10-04T15:32:32.572Z\",\n" + "  \"users\": [\n" + "    {\n"
				+ "   \"username\": \"abcdefghijklmnopqrst\",\n" + "      \"kanji\": true\n" + "    }\n" + "  ]\n" + "}";

		mockMvc.perform(post("/warikan").contentType(MediaType.APPLICATION_JSON).content(input))
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.Error.Message").value("Bad Request"));
	}

	@Test
	void tankoumoku_billingAmount() throws Exception {
		String input = "{\n" + "  \"activityName\": \"test\",\n" + "  \"billingAmount\": 2147483648,\n"
				+ "  \"activityDate\": \"2020-10-04T15:32:32.572Z\",\n" + "  \"users\": [\n" + "    {\n"
				+ "   \"username\": \"abcdefghijklmnopqrst\",\n" + "      \"kanji\": true\n" + "    }\n" + "  ]\n" + "}";

		mockMvc.perform(post("/warikan").contentType(MediaType.APPLICATION_JSON).content(input))
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.Error.Message").value("Bad Request"));
	}

	@Test
	void tankoumoku_username() throws Exception {
		String input = "{\n" + "  \"activityName\": \"test\",\n" + "  \"billingAmount\": 10000,\n"
				+ "  \"activityDate\": \"2020-10-04T15:32:32.572Z\",\n" + "  \"users\": [\n" + "    {\n"
				+ "   \"username\": \"abcdefghijklmnopqrstu\",\n" + "      \"kanji\": true\n" + "    }\n" + "  ]\n" + "}";

		mockMvc.perform(post("/warikan").contentType(MediaType.APPLICATION_JSON).content(input))
				.andExpect(status().isBadRequest()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.Error.Message").value("Bad Request"));
	}

	@Test
	@Sql(statements="insert into users(user_id, username, password, insert, update) values('3fa85f64-5717-4562-b3fc-2c963f66afa6', 'string', 'string', now(), now())")
	void success_id() throws Exception {
		String input = "{\n"
				+ "  \"activityName\": \"test activity name\",\n"
				+ "  \"billingAmount\": 9999999,\n"
				+ "  \"activityDate\": \"2020-10-07T14:43:29.884Z\",\n"
				+ "  \"users\": [\n"
				+ "    {\n"
				+ "      \"userId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n"
				+ "      \"kanji\": true\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"username\": \"test user name\",\n"
				+ "      \"kanji\": false\n"
				+ "    }\n"
				+ "  ]\n"
				+ "}";
		mockMvc.perform(post("/warikan").contentType(MediaType.APPLICATION_JSON).content(input))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.activityName").value("test activity name"))
				.andExpect(jsonPath("$.billingAmount").value(9999999))
				.andExpect(jsonPath("$.users[0].userId").value("3fa85f64-5717-4562-b3fc-2c963f66afa6"))
				.andExpect(jsonPath("$.users[0].username").value("string"))
				.andExpect(jsonPath("$.users[0].kanji").value(true))
				.andExpect(jsonPath("$.users[0].payAmount").value(5000000))
				.andExpect(jsonPath("$.users[1].username").value("test user name"))
				.andExpect(jsonPath("$.users[1].kanji").value(false))
				.andExpect(jsonPath("$.users[1].payAmount").value(4999999));
	}



}
