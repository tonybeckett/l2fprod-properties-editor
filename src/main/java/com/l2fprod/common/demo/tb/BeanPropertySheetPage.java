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
package com.l2fprod.common.demo.tb;

import com.l2fprod.common.beans.BeanBinder;
import com.l2fprod.common.propertysheet.PropertySheet;
import com.l2fprod.common.propertysheet.PropertySheetPanel;
import com.l2fprod.common.swing.LookAndFeelTweaks;

import java.awt.Color;
import java.util.ListResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * PropertySheetPage. <br>
 *
 */
public class BeanPropertySheetPage extends JPanel
{

    public BeanPropertySheetPage( GenericBean gb)
    {
        setLayout(LookAndFeelTweaks.createVerticalPercentLayout());

        JTextArea message = new JTextArea();
        message.setText("Bean Properties");
        LookAndFeelTweaks.makeMultilineLabel(message);
        add(message);

        final Bean data = new Bean(gb);

        final PropertySheetPanel sheet = new PropertySheetPanel();
        sheet.setMode(PropertySheet.VIEW_AS_CATEGORIES);
        sheet.setDescriptionVisible(true);
        sheet.setSortingCategories(true);
        sheet.setSortingProperties(true);
        sheet.setRestoreToggleStates(true);
        add(sheet, "*");

        // everytime a property change, update the sheet with it
        new BeanBinder(data, sheet);
    }

    public static class Bean
    {

        GenericBean genericBean;

        Bean(GenericBean gb)
        {
            genericBean = gb;
        }

        public int getWidth()
        {
            return genericBean.getWidth();
        }

        public void setWidth(int Width)
        {
            genericBean.setWidth(Width);
        }

        public int getHeight()
        {
            return genericBean.getHeight();
        }

        public void setHeight(int Height)
        {
            genericBean.setHeight(Height);
        }

        public Color getColor()
        {
            return genericBean.getColor();
        }

        public void setColor(Color Color)
        {
            genericBean.setColor(Color);
        }

    }

    public static class BeanRB extends ListResourceBundle
    {

        @Override
        protected Object[][] getContents()
        {
            return new Object[][]
            {
                {
                    "name", "Name"
                },
                {
                    "name.shortDescription", "The name of this object<br>Here I'm using multple lines<br>for the property<br>so scrollbars will get enabled"
                },
                {
                    "text", "Text"
                },
                {
                    "time", "Time"
                },
                {
                    "color", "Background"
                },
                {
                    "aDouble", "a double"
                },
                {
                    "season", "Season"
                },
                {
                    "constrained.shortDescription",
                    "This property is constrained. Try using <b>blah</b> as the value, the previous value will be restored"
                }
            };
        }
    }

}
