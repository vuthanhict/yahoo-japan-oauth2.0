/**
 * RANDOM STRING GENERATOR
 *
 * Info:      http://stackoverflow.com/a/27872144/383904
 * Use:       randomString(length [,"A"] [,"N"] );
 * Default:   return a random alpha-numeric string
 * Arguments: If you use the optional "A", "N" flags:
 *            "A" (Alpha flag)   return random a-Z string
 *            "N" (Numeric flag) return random 0-9 string
 */
function randomString(len, an){
    an = an&&an.toLowerCase();
    var str="", i=0, min=an=="a"?10:0, max=an=="n"?10:62;
    for(;i++<len;){
      var r = Math.random()*(max-min)+min <<0;
      str += String.fromCharCode(r+=r>9?r<36?55:61:48);
    }
    return str;
}

/**
* state = randomString(32, "an");
* Authorizationエンドポイント
*/
function yahooAuthorizationEndpoint(state) {
    var url = "https://auth.login.yahoo.co.jp/yconnect/v2/authorization?";
    url = url + "response_type=code";
    url = url + "&client_id=dj00aiZpPXZpOGo2ZHFIeW1YbyZzPWNvbnN1bWVyc2VjcmV0Jng9NzI-"
    url = url + "&redirect_uri={your_redirect_uri}"; // URLENCODE
    url = url + "&scope=openid%20profile%20email%20address";
    url = url + "&state=" + state;
    return url;
}
