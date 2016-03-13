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
package tbclock.editors;

import com.l2fprod.common.beans.editor.AbstractPropertyEditor;
import com.l2fprod.common.swing.ComponentFactory;
import com.l2fprod.common.swing.PercentLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * TBPropertyEditor. <br>
 *
 */
public class TBPropertyEditor extends AbstractPropertyEditor
{

    private final JButton button;
    //   private final Renderer label;
    private Object oldValue;
    private Object selectedValue;
    private Icon[] icons;
    private final Map<String, Object> map = new HashMap();
    private JComboBox combo;

    public TBPropertyEditor()
    {
        combo = new JComboBox()
        {
            @Override
            public void setSelectedItem(Object anObject)
            {
                oldValue = getSelectedItem();
                super.setSelectedItem(anObject);
                selectedValue = anObject;
            }

        };
        editor = new JPanel(new PercentLayout(PercentLayout.HORIZONTAL, 0));
        ((JPanel) editor).add(combo);
        //       ((JPanel) editor).add("*", label = new Renderer());
        //       label.setOpaque(false);
        ((JPanel) editor).add(button = ComponentFactory.Helper.getFactory()
                .createMiniButton());
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                editProperties(selectedValue);
            }
        });

        ((JPanel) editor).setOpaque(true);

        combo.setRenderer(new TBPropertyEditor.Renderer());
        combo.setEditable(false);
        combo.addPopupMenuListener(new PopupMenuListener()
        {
            @Override
            public void popupMenuCanceled(PopupMenuEvent e)
            {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
            {
                TBPropertyEditor.this.firePropertyChange(oldValue, combo.getSelectedItem());
            }

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {

            }
        });
        combo.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    TBPropertyEditor.this.firePropertyChange(oldValue, combo.getSelectedItem());
                }
            }
        });

        ArrayList<String> items = new ArrayList();

        List itemList = getItems();
        for (Object o : itemList)
        {
            items.add(o.getClass().getSimpleName());
            map.put(o.getClass().getSimpleName(), o);
        }
        setAvailableValues(items.toArray());

//        combo.setSelectedIndex(-1);
    }

    protected List getItems()
    {
        return new ArrayList();
    }

    public void setAvailableValues(Object[] values)
    {
        combo.setModel(new DefaultComboBoxModel(values));
    }

    public void setAvailableIcons(Icon[] icons)
    {
        this.icons = icons;
    }

    protected void editProperties(Object selectedItem)
    {

//        ResourceManager rm = ResourceManager.all(FilePropertyEditor.class);
//        String title = rm.getString("TBPropertyEditor.title");
//        Color selectedColor = JColorChooser.showDialog(editor, title, color);
//
//        if (selectedColor != null)
//        {
//            Color oldColor = color;
//            Color newColor = selectedColor;
////            label.setValue(newColor);
//            color = newColor;
//            firePropertyChange(oldColor, newColor);
//        }
    }

    @Override
    public Object getValue()
    {
        Object selected = combo.getSelectedItem();
        if (selected instanceof Value)
        {
            return ((Value) selected).value;
        } else
        {
            return map.get((String) selected);
        }
    }

    @Override
    public void setValue(Object value)
    {

        Object current = null;
        int index = -1;
        for (int i = 0, c = combo.getModel().getSize(); i < c; i++)
        {
            current = combo.getModel().getElementAt(i);
            if (value == current || (current != null && current.equals(value)))
            {
                index = i;
                break;
            }
        }
        combo.setSelectedIndex(index);
    }

    public class Renderer extends DefaultListCellRenderer
    {

        @Override
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus)
        {
            Component component = super.getListCellRendererComponent(
                    list,
                    (value instanceof TBPropertyEditor.Value) ? ((TBPropertyEditor.Value) value).visualValue : value,
                    index,
                    isSelected,
                    cellHasFocus);
            if (icons != null && index >= 0 && component instanceof JLabel)
            {
                ((JLabel) component).setIcon(icons[index]);
            }
            return component;
        }
    }

    public static class AsInt extends TBPropertyEditor
    {

//        @Override
//        public void setValue(Object arg0)
//        {
//            if (arg0 instanceof Integer)
//            {
//                super.setValue(new Color(((Integer) arg0).intValue()));
//            } else
//            {
//                super.setValue(arg0);
//            }
//        }
//
//        @Override
//        public Object getValue()
//        {
//            Object value = super.getValue();
//            if (value == null)
//            {
//                return null;
//            } else
//            {
//                return new Integer(((Color) value).getRGB());
//            }
//        }
        @Override
        protected void firePropertyChange(Object oldValue, Object newValue)
        {
//            if (oldValue instanceof Color)
//            {
//                oldValue = new Integer(((Color) oldValue).getRGB());
//            }
//            if (newValue instanceof Color)
//            {
//                newValue = new Integer(((Color) newValue).getRGB());
//            }
            super.firePropertyChange(oldValue, newValue);
        }
    }

    public static final class Value
    {

        private Object value;
        public Object visualValue;

        public Value(Object value, Object visualValue)
        {
            this.value = value;
            this.visualValue = visualValue;
        }

        @Override
        public boolean equals(Object o)
        {
            if (o == this)
            {
                return true;
            }
            if (value == o || (value != null && value.equals(o)))
            {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode()
        {
            return value == null ? 0 : value.hashCode();
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
                    "frame", "Frame"
                },
                {
                    "constrained.shortDescription",
                    "This property is constrained. Try using <b>blah</b> as the value, the previous value will be restored"
                }
            };
        }
    }
}
