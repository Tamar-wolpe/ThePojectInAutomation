package pages;

public enum MainMenu {
    KITSBAOT("קצבאות והטבות"),
    BRANCHES("סניפים ושירות"),
    INSURANCE_FEE("דמי ביטוח"),
    FORMS("טפסים"),
    CALCULATORS("מחשבונים");

    private String text;
    MainMenu(String text) { this.text = text; }
    public String getText() { return text; }
}