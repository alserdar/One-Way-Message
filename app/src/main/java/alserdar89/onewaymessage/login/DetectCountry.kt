package alserdar89.onewaymessage.login

import alserdar89.onewaymessage.loading_and_toast.OurToast
import alserdar89.onewaymessage.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.telephony.TelephonyManager

class DetectCountry : AppCompatActivity() {

    internal lateinit var telephonyManager: TelephonyManager
    internal lateinit var Country: String
    internal var dialCode: String? = null
    internal lateinit var currentCountry: String
    internal var simState: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detect_country)
        telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        simState = telephonyManager.simState
        when (simState) {
            TelephonyManager.SIM_STATE_ABSENT -> OurToast.myToast(
                baseContext,
                "SimCard is absent"
            )

            TelephonyManager.SIM_STATE_NETWORK_LOCKED -> OurToast.myToast(
                baseContext,
                "SimCard is locked"
            )

            TelephonyManager.SIM_STATE_PIN_REQUIRED -> OurToast.myToast(
                baseContext,
                "SimCard is pin required"
            )

            TelephonyManager.SIM_STATE_PUK_REQUIRED -> OurToast.myToast(
                baseContext,
                "SimCard is puk required"
            )

            TelephonyManager.SIM_STATE_UNKNOWN -> OurToast.myToast(
                baseContext,
                "SimCard is Unknown"
            )

            TelephonyManager.SIM_STATE_READY -> {
                //new OurToast().myToast(getBaseContext() ,"SimCard is READY" );
                try {
                    Country = telephonyManager.simCountryIso
                    if (Country != null && Country.length == 2) {

                        // Toast.makeText(getBaseContext(), Country.toUpperCase(), Toast.LENGTH_LONG).show();
                    } else if (telephonyManager.phoneType != TelephonyManager.PHONE_TYPE_CDMA) {
                        Country = telephonyManager.networkCountryIso
                        if (Country != null && Country.length == 2) {
                            OurToast.myToast(
                                baseContext,
                                currentCountry
                            )
                        }
                    }
                } catch (e: Exception) {
                    e.message
                }

                if (Country != null) {
                    when (Country.toUpperCase()) {

                        "EG" -> currentCountry = "Egypt"
                        "AF" -> currentCountry = "Afghanistan"
                        "AX" -> currentCountry = "Aland Islands" // withour flag
                        "AL" -> currentCountry = "Albania"
                        "DZ" -> currentCountry = "Algeria"
                        "AS" -> currentCountry = "American Samoa"
                        "AD" -> currentCountry = "Andorra"
                        "AO" -> currentCountry = "Angola"
                        "AI" -> currentCountry = "Anguilla"
                        "AQ" -> currentCountry = "Antarctica" // withour flag
                        "AG" -> currentCountry = "Antigua and Barbuda"
                        "AR" -> currentCountry = "Argentina"
                        "AM" -> currentCountry = "Armenia"
                        "AW" -> currentCountry = "Aruba"
                        "AC" -> currentCountry = "Ascension Island"
                        "AU" -> currentCountry = "Australia"
                        "AT" -> currentCountry = "Austria"
                        "AZ" -> currentCountry = "Azerbaijan"
                        "BS" -> currentCountry = "Bahamas"
                        "BH" -> currentCountry = "Bahrain"
                        "BB" -> currentCountry = "Barbados"
                        "BD" -> currentCountry = "Bangladesh"
                        "BY" -> currentCountry = "Belarus"
                        "BE" -> currentCountry = "Belgium"
                        "BZ" -> currentCountry = "Belize"
                        "BJ" -> currentCountry = "Benin"
                        "BM" -> currentCountry = "Bermuda"
                        "BT" -> currentCountry = "Bhutan"
                        "BW" -> currentCountry = "Botswana"

                        "BO" -> currentCountry = "Bolivia"
                        "BA" -> currentCountry = "Bosnia and Herzegovina"
                        "BV" -> currentCountry = "Bouvet Island"
                        "BR" -> currentCountry = "Brazil"
                        "IO" -> currentCountry = "British Indian Ocean Territory" // withour flag
                        "BN" -> currentCountry = "Brunei Darussalam"
                        "BG" -> currentCountry = "Bulgaria"
                        "BF" -> currentCountry = "Burkina Faso"

                        "BI" -> currentCountry = "Burundi"
                        "KH" -> currentCountry = "Cambodia"
                        "CM" -> currentCountry = "Cameroon"
                        "CA" -> currentCountry = "Canada"
                        "CV" -> currentCountry = "Cape Verde"
                        "KY" -> currentCountry = "Cayman Islands"
                        "CF" -> currentCountry = "Central African Republic"
                        "TD" -> currentCountry = "Chad"
                        "CL" -> currentCountry = "Chile"
                        "CN" -> currentCountry = "China"
                        "CX" -> currentCountry = "Christmas Island"
                        "CC" -> currentCountry = "Cocos (Keeling) Islands"
                        "CO" -> currentCountry = "Colombia"
                        "KM" -> currentCountry = "Comoros"
                        "CG" -> currentCountry = "Congo"
                        "CD" -> currentCountry = "Congo, Democratic Republic"
                        "CK" -> currentCountry = "Cook Islands"
                        "CR" -> currentCountry = "Costa Rica"
                        "CI" -> currentCountry = "Cote D'Ivoire (Ivory Coast)"
                        "HR" -> currentCountry = "Croatia (Hrvatska)"
                        "CU" -> currentCountry = "Cuba"
                        "CY" -> currentCountry = "Cyprus"
                        "CZ" -> currentCountry = "Czech Republic"
                        "CS" -> currentCountry = "Czechoslovakia (former)"
                        "DK" -> currentCountry = "Denmark"
                        "DJ" -> currentCountry = "Djibouti"
                        "DM" -> currentCountry = "Dominica"
                        "DO" -> currentCountry = "Dominican Republic"
                        "TP" -> currentCountry = "East Timor"
                        "EC" -> currentCountry = "Ecuador"
                        "SV" -> currentCountry = "El Salvador"
                        "GQ" -> currentCountry = "Equatorial Guinea"
                        "ER" -> currentCountry = "Eritrea"
                        "EE" -> currentCountry = "Estonia"
                        "ET" -> currentCountry = "Ethiopia"
                        "FK" -> currentCountry = "Falkland Islands (Malvinas)"
                        "FO" -> currentCountry = "Faroe Islands"
                        "FJ" -> currentCountry = "Fiji"
                        "FI" -> currentCountry = "Finland"
                        "FR" -> currentCountry = "France"
                        "FX" -> currentCountry = "France, Metropolitan"
                        "GF" -> currentCountry = "French Guiana"
                        "PF" -> currentCountry = "French Polynesia"
                        "TF" -> currentCountry = "French Southern Territories"
                        "MK" -> currentCountry = "Macedonia"
                        "GA" -> currentCountry = "Gabon"
                        "GM" -> currentCountry = "Gambia"
                        "GE" -> currentCountry = "Georgia"
                        "DE" -> currentCountry = "Germany"
                        "GH" -> currentCountry = "Ghana"
                        "GI" -> currentCountry = "Gibraltar"
                        "GB" -> currentCountry = "Great Britain (UK)"
                        "GR" -> currentCountry = "Greece"
                        "GL" -> currentCountry = "Greenland"
                        "GD" -> currentCountry = "Grenada"
                        "GP" -> currentCountry = "Guadeloupe"
                        "GU" -> currentCountry = "Guam"
                        "GT" -> currentCountry = "Guatemala"
                        "GG" -> currentCountry = "Guernsey"
                        "GN" -> currentCountry = "Guinea"
                        "GW" -> currentCountry = "Guinea-Bissau"
                        "GY" -> currentCountry = "Guyana"
                        "HT" -> currentCountry = "Haiti"
                        "HM" -> currentCountry = "Heard and McDonald Islands"
                        "HN" -> currentCountry = "Honduras"
                        "HK" -> currentCountry = "Hong Kong"
                        "HU" -> currentCountry = "Hungary"
                        "IS" -> currentCountry = "Iceland"
                        "IN" -> currentCountry = "India"
                        "ID" -> currentCountry = "Indonesia"
                        "IR" -> currentCountry = "Iran"
                        "IQ" -> currentCountry = "Iraq"
                        "IE" -> currentCountry = "Ireland"
                        "IL" -> currentCountry = "Palestine"
                        "IM" -> currentCountry = "Isle of Man"
                        "IT" -> currentCountry = "Italy"
                        "JE" -> currentCountry = "Jersey"
                        "JM" -> currentCountry = "Jamaica"
                        "JP" -> currentCountry = "Japan"
                        "JO" -> currentCountry = "Jordan"
                        "KZ" -> currentCountry = "Kazakhstan"
                        "KE" -> currentCountry = "Kenya"
                        "KI" -> currentCountry = "Kiribati"
                        "KP" -> currentCountry = "Korea (North)"
                        "KR" -> currentCountry = "Korea (South)"
                        "KW" -> currentCountry = "Kuwait"
                        "KG" -> currentCountry = "Kyrgyzstan"
                        "LA" -> currentCountry = "Laos"
                        "LV" -> currentCountry = "Latvia"
                        "LB" -> currentCountry = "Lebanon"
                        "LI" -> currentCountry = "Liechtenstein"
                        "LR" -> currentCountry = "Liberia"
                        "LY" -> currentCountry = "Libya"
                        "LS" -> currentCountry = "Lesotho"
                        "LT" -> currentCountry = "Lithuania"
                        "LU" -> currentCountry = "Luxembourg"
                        "MO" -> currentCountry = "Macau"
                        "MG" -> currentCountry = "Madagascar"
                        "MW" -> currentCountry = "Malawi"
                        "MY" -> currentCountry = "Malaysia"
                        "MV" -> currentCountry = "Maldives"
                        "ML" -> currentCountry = "Mali"
                        "MT" -> currentCountry = "Malta"
                        "MH" -> currentCountry = "Marshall Islands"
                        "MQ" -> currentCountry = "martinique"
                        "MR" -> currentCountry = "mauritania"
                        "MU" -> currentCountry = "mauritius"
                        "YT" -> currentCountry = "Mayotte"
                        "MX" -> currentCountry = "mexico"
                        "FM" -> currentCountry = "micronesia"
                        "MC" -> currentCountry = "monaco"
                        "MD" -> currentCountry = "moldova"
                        "MN" -> currentCountry = "mongolia"
                        "ME" -> currentCountry = "Montenegro"
                        "MS" -> currentCountry = "montserrat"
                        "MA" -> currentCountry = "morocco"
                        "MZ" -> currentCountry = "Mozambique"
                        "MM" -> currentCountry = "Myanmar"
                        "NA" -> currentCountry = "Namibia"
                        "NR" -> currentCountry = "Nauru"
                        "NP" -> currentCountry = "Nepal"
                        "NL" -> currentCountry = "Netherlands"
                        "AN" -> currentCountry = "Netherlands Antilles"
                        "NT" -> currentCountry = "Neutral Zone"
                        "NC" -> currentCountry = "New Caledonia"
                        "NZ" -> currentCountry = "New Zealand (Aotearoa)"
                        "NI" -> currentCountry = "Nicaragua"
                        "NE" -> currentCountry = "Niger"
                        "NG" -> currentCountry = "Nigeria"
                        "NU" -> currentCountry = "Niue"
                        "NF" -> currentCountry = "Norfolk Island"
                        "MP" -> currentCountry = "Northern Mariana Islands"
                        "NO" -> currentCountry = "Norway"
                        "OM" -> currentCountry = "Oman"
                        "PK" -> currentCountry = "Pakistan"
                        "PW" -> currentCountry = "Palau"
                        "PS" -> currentCountry = "Palestine"
                        "PA" -> currentCountry = "Panama"
                        "PG" -> currentCountry = "Papua New Guinea"
                        "PY" -> currentCountry = "Paraguay"
                        "PE" -> currentCountry = "Peru"
                        "PH" -> currentCountry = "Philippines"
                        "PN" -> currentCountry = "Pitcairn"
                        "PL" -> currentCountry = "Poland"
                        "PT" -> currentCountry = "Portugal"
                        "PR" -> currentCountry = "Puerto Rico"
                        "QA" -> currentCountry = "Qatar"
                        "RE" -> currentCountry = "Reunion"
                        "RO" -> currentCountry = "Romania"
                        "RU" -> currentCountry = "Russian Federation"
                        "RW" -> currentCountry = "Rwanda"
                        "GS" -> currentCountry = "S. Georgia"
                        "KN" -> currentCountry = "Saint Kitts and Nevis"
                        "LC" -> currentCountry = "Saint Lucia"
                        "VC" -> currentCountry = "Saint Vincent & the Grenadines"
                        "WS" -> currentCountry = "Samoa"
                        "SM" -> currentCountry = "San Marino"
                        "ST" -> currentCountry = "Sao Tome and Principe"
                        "SA" -> currentCountry = "Saudi Arabia"
                        "SN" -> currentCountry = "Senegal"
                        "RS" -> currentCountry = "Serbia"
                        "YU" -> currentCountry = "Serbia and Montenegro"
                        "SC" -> currentCountry = "Seychelles"
                        "SL" -> currentCountry = "Sierra Leone"
                        "SG" -> currentCountry = "Singapore"
                        "SI" -> currentCountry = "Slovenia"
                        "SK" -> currentCountry = "Slovak Republic"
                        "SB" -> currentCountry = "Solomon Islands"
                        "SO" -> currentCountry = "Somalia"
                        "ZA" -> currentCountry = "South Africa"
                        "ES" -> currentCountry = "Spain"
                        "LK" -> currentCountry = "Sri Lanka"
                        "SH" -> currentCountry = "St. Helena"
                        "PM" -> currentCountry = "St. Pierre and Miquelon"
                        "SD" -> currentCountry = "Sudan"
                        "SR" -> currentCountry = "Suriname"
                        "SJ" -> currentCountry = "Svalbard & Jan Mayen Islands"
                        "SZ" -> currentCountry = "Swaziland"
                        "SE" -> currentCountry = "Sweden"
                        "CH" -> currentCountry = "Switzerland"
                        "SY" -> currentCountry = "Syria"
                        "TW" -> currentCountry = "Taiwan"
                        "TJ" -> currentCountry = "Tajikistan"
                        "TZ" -> currentCountry = "Tanzania"
                        "TH" -> currentCountry = "Thailand"
                        "TG" -> currentCountry = "Togo"
                        "TK" -> currentCountry = "Tokelau"
                        "TO" -> currentCountry = "Tonga"
                        "TT" -> currentCountry = "Trinidad and Tobago"
                        "TN" -> currentCountry = "Tunisia"
                        "TR" -> currentCountry = "Turkey"
                        "TM" -> currentCountry = "Turkmenistan"
                        "TC" -> currentCountry = "Turks and Caicos Islands"
                        "TV" -> currentCountry = "Tuvalu"
                        "UG" -> currentCountry = "Uganda"
                        "UA" -> currentCountry = "Ukraine"
                        "AE" -> currentCountry = "United Arab Emirates"
                        "UK" -> currentCountry = "United Kingdom"
                        "US" -> currentCountry = "United States"
                        "UM" -> currentCountry = "US Minor Outlying Islands"
                        "UY" -> currentCountry = "Uruguay"
                        "SU" -> currentCountry = "USSR (former)"
                        "UZ" -> currentCountry = "Uzbekistan"
                        "VU" -> currentCountry = "Vanuatu"
                        "VA" -> currentCountry = "Vatican City State"
                        "VE" -> currentCountry = "Venezuela"
                        "VN" -> currentCountry = "Vietnam"
                        "VG" -> currentCountry = "British Virgin Islands"
                        "VI" -> currentCountry = "Virgin Islands (U.S.)"
                        "WF" -> currentCountry = "Wallis and Futuna Islands"
                        "EH" -> currentCountry = "Western Sahara"
                        "YE" -> currentCountry = "Yemen"
                        "ZM" -> currentCountry = "Zambia"
                        "ZR" -> currentCountry = "Zaire"
                        "ZW" -> currentCountry = "Zimbabwe"
                        else -> currentCountry = "Unknown Country"
                    }
                }
            }
        }

        Thread(Runnable {

            val privateOrNotDetails = PreferenceManager.getDefaultSharedPreferences(this@DetectCountry)
            val editor = privateOrNotDetails.edit()
            editor.putString("country", currentCountry)
            editor.apply()
            val i = Intent(baseContext, GoogleSignInActivity::class.java)
            startActivity(i)
            finish()
        }).start()
    }


    override fun onBackPressed() {

    }

}