package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.FieldStyle;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test FieldStyleAndSubTypeConverter.
 *
 * User: jessica
 * Date: 19/11/13
 * Time: 3:42 PM
 */
public class FieldStyleAndSubTypeConverterTest implements ConverterTest{

    private String apiFieldSubtype1 = null;
    private String apiFieldSubtype2 = null;
    private com.silanis.esl.sdk.FieldStyle sdkFieldStyle1=null;
    private com.silanis.esl.sdk.FieldStyle sdkFieldStyle2=null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkFieldStyle1 = null;
        FieldStyleAndSubTypeConverter converter = new FieldStyleAndSubTypeConverter(sdkFieldStyle1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPIFieldSubtype(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiFieldSubtype1 = null;
        String binding = null;
        FieldStyleAndSubTypeConverter converter = new FieldStyleAndSubTypeConverter(apiFieldSubtype1, binding);
        assertThat( "Converter didn't return a null sdk object for a null api object", converter.toSDKFieldStyle(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkFieldStyle1 = null;
        FieldStyleAndSubTypeConverter converter = new FieldStyleAndSubTypeConverter(sdkFieldStyle1);
        assertThat( "Converter didn't return a null sdk object for a null sdk object", converter.toSDKFieldStyle(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiFieldSubtype1 = null;
        String binding = null;
        FieldStyleAndSubTypeConverter converter = new FieldStyleAndSubTypeConverter(apiFieldSubtype1, binding);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPIFieldSubtype(), is( nullValue() ) );
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkFieldStyle1  = FieldStyle.UNBOUND_CUSTOM_FIELD;
        sdkFieldStyle2  = new FieldStyleAndSubTypeConverter(sdkFieldStyle1).toSDKFieldStyle();
        assertThat( "Converter returned a null sdk object for a non null sdk object", sdkFieldStyle2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null sdk object it was given", sdkFieldStyle2, is( equalTo( sdkFieldStyle1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        String binding = null;
        apiFieldSubtype1  = "CUSTOMFIELD";
        apiFieldSubtype2 = new FieldStyleAndSubTypeConverter(apiFieldSubtype1, binding).toAPIFieldSubtype();

        assertThat( "Converter returned a null api object for a non null api object", apiFieldSubtype2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiFieldSubtype2, is( equalTo( apiFieldSubtype1 ) ) );
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        String BINDING_DATE = "{approval.signed}";
        String BINDING_TITLE = "{signer.title}";
        String BINDING_NAME = "{signer.name}";
        String BINDING_COMPANY = "{signer.company}";

        // Where the conversion is based on subtype.
        String binding;
        String fieldSubtype;
        FieldStyle fieldStyle;

        fieldSubtype = "CUSTOMFIELD";
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat(" Custom Field type was not correctly set", fieldStyle, is( equalTo(FieldStyle.UNBOUND_CUSTOM_FIELD)));

        fieldSubtype = "TEXTFIELD";
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("Text field type  was not correctly set", fieldStyle, is( equalTo(FieldStyle.UNBOUND_TEXT_FIELD)));

        fieldSubtype = "CHECKBOX";
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("Checkbox type was not correctly set", fieldStyle, is( equalTo(FieldStyle.UNBOUND_CHECK_BOX)));

        fieldSubtype = "RADIO";
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("Radio button type was not correctly set", fieldStyle, is( equalTo(FieldStyle.UNBOUND_RADIO_BUTTON)));

        fieldSubtype = "TEXTAREA";
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("Text area type was not correctly set", fieldStyle, is( equalTo(FieldStyle.TEXT_AREA)));

        fieldSubtype = "LIST";
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("List type was not correctly set", fieldStyle, is( equalTo(FieldStyle.DROP_LIST)));

        fieldSubtype = "QRCODE";
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("QR code type was not correctly set", fieldStyle, is( equalTo(FieldStyle.BOUND_QRCODE)));

        fieldSubtype = "SEAL";
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("Seal type was not correctly set", fieldStyle, is( equalTo(FieldStyle.SEAL)));

        fieldSubtype = "LABEL";
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("Label type was not correctly set", fieldStyle, is( equalTo(FieldStyle.LABEL)));

        fieldSubtype = "UNKNOWN";
        binding = null;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("Unknown type was not correctly set", fieldStyle.toString(), is( equalTo(FieldStyle.UNRECOGNIZED("UNKNOWN").toString())));

        // Where the conversion is based on binding.
        fieldSubtype = null;
        binding = BINDING_DATE;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("Date type was not correctly set", fieldStyle, is( equalTo(FieldStyle.BOUND_DATE)));

        fieldSubtype = null;
        binding = BINDING_TITLE;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("Title type was not correctly set", fieldStyle, is( equalTo(FieldStyle.BOUND_TITLE)));

        fieldSubtype = null;
        binding = BINDING_NAME;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat("Name type was not correctly set", fieldStyle, is( equalTo(FieldStyle.BOUND_NAME)));

        fieldSubtype = null;
        binding = BINDING_COMPANY;
        fieldStyle = new FieldStyleAndSubTypeConverter(fieldSubtype, binding).toSDKFieldStyle();
        assertThat(" was not correctly set", fieldStyle, is( equalTo(FieldStyle.BOUND_COMPANY)));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        FieldStyle fieldStyle = FieldStyle.UNBOUND_CUSTOM_FIELD;
        String fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("CUSTOMFIELD")));

        fieldStyle = FieldStyle.UNBOUND_TEXT_FIELD;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("TEXTFIELD")));

        fieldStyle = FieldStyle.LABEL;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("LABEL")));

        fieldStyle = FieldStyle.UNBOUND_CHECK_BOX;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("CHECKBOX")));

        fieldStyle = FieldStyle.UNBOUND_RADIO_BUTTON;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("RADIO")));

        fieldStyle = FieldStyle.DROP_LIST;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("LIST")));

        fieldStyle = FieldStyle.TEXT_AREA;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("TEXTAREA")));

        fieldStyle = FieldStyle.BOUND_QRCODE;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("QRCODE")));

        fieldStyle = FieldStyle.SEAL;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("SEAL")));

        fieldStyle = FieldStyle.LABEL;
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("LABEL")));

        fieldStyle = FieldStyle.UNRECOGNIZED("UNKNOWN");
        fieldSubtype = new FieldStyleAndSubTypeConverter(fieldStyle).toAPIFieldSubtype();
        assertThat(" was not correctly set", fieldSubtype, is( equalTo("UNKNOWN")));
    }

}
