package cn.edu.nju.lc.springstudy.conversion;

public class PropertyEditorDemo {

    public static void main(String[] args) {

        String text = "id=1";
        StringToPropertiesPropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();

        propertyEditor.setAsText(text);
        System.out.println(propertyEditor.getValue());
        System.out.println(propertyEditor.getAsText());
    }

}
