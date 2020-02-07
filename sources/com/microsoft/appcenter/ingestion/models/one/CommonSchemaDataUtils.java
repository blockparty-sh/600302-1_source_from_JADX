package com.microsoft.appcenter.ingestion.models.one;

import androidx.annotation.VisibleForTesting;
import com.microsoft.appcenter.ingestion.models.json.JSONDateUtils;
import com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.DateTimeTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.StringTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.TypedProperty;
import com.microsoft.appcenter.utils.AppCenterLog;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CommonSchemaDataUtils {
    @VisibleForTesting
    static final int DATA_TYPE_DATETIME = 9;
    @VisibleForTesting
    static final int DATA_TYPE_DOUBLE = 6;
    @VisibleForTesting
    static final int DATA_TYPE_INT64 = 4;
    @VisibleForTesting
    static final String METADATA_FIELDS = "f";

    public static void addCommonSchemaData(List<TypedProperty> list, CommonSchemaLog commonSchemaLog) {
        String str;
        Iterator it;
        String str2;
        String str3;
        CommonSchemaLog commonSchemaLog2 = commonSchemaLog;
        String str4 = "baseType";
        String str5 = "baseData";
        if (list != null) {
            try {
                Data data = new Data();
                commonSchemaLog2.setData(data);
                MetadataExtension metadataExtension = new MetadataExtension();
                Iterator it2 = list.iterator();
                while (true) {
                    str = "AppCenter";
                    if (!it2.hasNext()) {
                        break;
                    }
                    TypedProperty typedProperty = (TypedProperty) it2.next();
                    try {
                        Object validateProperty = validateProperty(typedProperty);
                        Integer metadataType = getMetadataType(typedProperty);
                        String[] split = typedProperty.getName().split("\\.", -1);
                        int length = split.length - 1;
                        JSONObject properties = data.getProperties();
                        JSONObject metadata = metadataExtension.getMetadata();
                        int i = 0;
                        while (true) {
                            str2 = "' already has a value, the old value will be overridden.";
                            str3 = "Property key '";
                            if (i >= length) {
                                break;
                            }
                            Iterator it3 = it2;
                            String str6 = split[i];
                            JSONObject optJSONObject = properties.optJSONObject(str6);
                            if (optJSONObject == null) {
                                if (properties.has(str6)) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(str3);
                                    sb.append(str6);
                                    sb.append(str2);
                                    AppCenterLog.warn(str, sb.toString());
                                }
                                JSONObject jSONObject = new JSONObject();
                                properties.put(str6, jSONObject);
                                properties = jSONObject;
                            } else {
                                properties = optJSONObject;
                            }
                            metadata = addIntermediateMetadata(metadata, str6);
                            i++;
                            it2 = it3;
                            CommonSchemaLog commonSchemaLog3 = commonSchemaLog;
                        }
                        it = it2;
                        String str7 = split[length];
                        if (properties.has(str7)) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str3);
                            sb2.append(str7);
                            sb2.append(str2);
                            AppCenterLog.warn(str, sb2.toString());
                        }
                        properties.put(str7, validateProperty);
                        addLeafMetadata(metadataType, metadata, str7);
                    } catch (IllegalArgumentException e) {
                        it = it2;
                        AppCenterLog.warn(str, e.getMessage());
                    }
                    it2 = it;
                    CommonSchemaLog commonSchemaLog4 = commonSchemaLog;
                }
                JSONObject properties2 = data.getProperties();
                String optString = properties2.optString(str4, null);
                JSONObject optJSONObject2 = properties2.optJSONObject(str5);
                if (optString == null && optJSONObject2 != null) {
                    AppCenterLog.warn(str, "baseData was set but baseType is missing.");
                    properties2.remove(str5);
                    metadataExtension.getMetadata().optJSONObject(METADATA_FIELDS).remove(str5);
                }
                if (optString != null && optJSONObject2 == null) {
                    AppCenterLog.warn(str, "baseType was set but baseData is missing.");
                    properties2.remove(str4);
                }
                if (!cleanUpEmptyObjectsInMetadata(metadataExtension.getMetadata())) {
                    if (commonSchemaLog.getExt() == null) {
                        commonSchemaLog.setExt(new Extensions());
                    } else {
                        CommonSchemaLog commonSchemaLog5 = commonSchemaLog;
                    }
                    commonSchemaLog.getExt().setMetadata(metadataExtension);
                }
            } catch (JSONException unused) {
            }
        }
    }

    private static Object validateProperty(TypedProperty typedProperty) throws IllegalArgumentException, JSONException {
        Object obj;
        String name = typedProperty.getName();
        if (name != null) {
            String str = "baseType must be a string.";
            if (name.equals("baseType") && !(typedProperty instanceof StringTypedProperty)) {
                throw new IllegalArgumentException(str);
            } else if (name.startsWith("baseType.")) {
                throw new IllegalArgumentException(str);
            } else if (!name.equals("baseData")) {
                if (typedProperty instanceof StringTypedProperty) {
                    obj = ((StringTypedProperty) typedProperty).getValue();
                } else if (typedProperty instanceof LongTypedProperty) {
                    obj = Long.valueOf(((LongTypedProperty) typedProperty).getValue());
                } else if (typedProperty instanceof DoubleTypedProperty) {
                    obj = Double.valueOf(((DoubleTypedProperty) typedProperty).getValue());
                } else if (typedProperty instanceof DateTimeTypedProperty) {
                    obj = JSONDateUtils.toString(((DateTimeTypedProperty) typedProperty).getValue());
                } else if (typedProperty instanceof BooleanTypedProperty) {
                    obj = Boolean.valueOf(((BooleanTypedProperty) typedProperty).getValue());
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unsupported property type: ");
                    sb.append(typedProperty.getType());
                    throw new IllegalArgumentException(sb.toString());
                }
                if (obj != null) {
                    return obj;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Value of property with key '");
                sb2.append(name);
                sb2.append("' cannot be null.");
                throw new IllegalArgumentException(sb2.toString());
            } else {
                throw new IllegalArgumentException("baseData must be an object.");
            }
        } else {
            throw new IllegalArgumentException("Property key cannot be null.");
        }
    }

    private static Integer getMetadataType(TypedProperty typedProperty) {
        if (typedProperty instanceof LongTypedProperty) {
            return Integer.valueOf(4);
        }
        if (typedProperty instanceof DoubleTypedProperty) {
            return Integer.valueOf(6);
        }
        if (typedProperty instanceof DateTimeTypedProperty) {
            return Integer.valueOf(9);
        }
        return null;
    }

    private static void addLeafMetadata(Integer num, JSONObject jSONObject, String str) throws JSONException {
        String str2 = METADATA_FIELDS;
        JSONObject optJSONObject = jSONObject.optJSONObject(str2);
        if (num != null) {
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
                jSONObject.put(str2, optJSONObject);
            }
            optJSONObject.put(str, num);
        } else if (optJSONObject != null) {
            optJSONObject.remove(str);
        }
    }

    private static JSONObject addIntermediateMetadata(JSONObject jSONObject, String str) throws JSONException {
        String str2 = METADATA_FIELDS;
        JSONObject optJSONObject = jSONObject.optJSONObject(str2);
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
            jSONObject.put(str2, optJSONObject);
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject(str);
        if (optJSONObject2 != null) {
            return optJSONObject2;
        }
        JSONObject jSONObject2 = new JSONObject();
        optJSONObject.put(str, jSONObject2);
        return jSONObject2;
    }

    private static boolean cleanUpEmptyObjectsInMetadata(JSONObject jSONObject) {
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            JSONObject optJSONObject = jSONObject.optJSONObject((String) keys.next());
            if (optJSONObject != null && cleanUpEmptyObjectsInMetadata(optJSONObject)) {
                keys.remove();
            }
        }
        return jSONObject.length() == 0;
    }
}
