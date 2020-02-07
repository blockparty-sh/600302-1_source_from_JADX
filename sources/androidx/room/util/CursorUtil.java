package androidx.room.util;

import android.database.Cursor;
import android.database.MatrixCursor;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP_PREFIX})
public class CursorUtil {
    @NonNull
    public static Cursor copyAndClose(@NonNull Cursor cursor) {
        try {
            MatrixCursor matrixCursor = new MatrixCursor(cursor.getColumnNames(), cursor.getCount());
            while (cursor.moveToNext()) {
                Object[] objArr = new Object[cursor.getColumnCount()];
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    int type = cursor.getType(i);
                    if (type == 0) {
                        objArr[i] = null;
                    } else if (type == 1) {
                        objArr[i] = Long.valueOf(cursor.getLong(i));
                    } else if (type == 2) {
                        objArr[i] = Double.valueOf(cursor.getDouble(i));
                    } else if (type == 3) {
                        objArr[i] = cursor.getString(i);
                    } else if (type == 4) {
                        objArr[i] = cursor.getBlob(i);
                    } else {
                        throw new IllegalStateException();
                    }
                }
                matrixCursor.addRow(objArr);
            }
            return matrixCursor;
        } finally {
            cursor.close();
        }
    }

    public static int getColumnIndex(@NonNull Cursor cursor, @NonNull String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        StringBuilder sb = new StringBuilder();
        String str2 = "`";
        sb.append(str2);
        sb.append(str);
        sb.append(str2);
        return cursor.getColumnIndex(sb.toString());
    }

    public static int getColumnIndexOrThrow(@NonNull Cursor cursor, @NonNull String str) {
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex >= 0) {
            return columnIndex;
        }
        StringBuilder sb = new StringBuilder();
        String str2 = "`";
        sb.append(str2);
        sb.append(str);
        sb.append(str2);
        return cursor.getColumnIndexOrThrow(sb.toString());
    }

    private CursorUtil() {
    }
}
