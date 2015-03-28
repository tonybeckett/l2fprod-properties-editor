/*
 * Copyright 2015 Matthew Aguirre
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.l2fprod.common.swing.renderer;

import com.l2fprod.common.annotations.RendererRegistry;
import com.l2fprod.common.beans.editor.DateStringPropertyEditor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A renderer for Date.
 *
 * @author Ricardo Lopes
 */
@RendererRegistry(type = Date.class)
public class DateRenderer extends DefaultCellRenderer {

    private DateFormat dateFormat;

    public DateRenderer() {
        this(DateStringPropertyEditor.DEFAULT_DATE_FORMAT);
    }

    public DateRenderer(String formatString) {
        this(formatString, Locale.getDefault());
    }

    public DateRenderer(Locale l) {
        this(DateFormat.getDateInstance(DateFormat.SHORT, l));
    }

    public DateRenderer(String formatString, Locale l) {
        this(new SimpleDateFormat(formatString, l));
    }

    public DateRenderer(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public void setValue(Object value) {
        if (value == null) {
            setText("");
        } else {
            setText(dateFormat.format((Date) value));
        }
    }

}
