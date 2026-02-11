package pages;

public enum MainMenu {
    KITSBAOT("קצבאות והטבות"),
    BRANCHES("סניפים וערוצי שירות"),
    INSURANCE_FEE("דמי ביטוח"),
    FORMS("טפסים ואישורים"),
    ABOUT("אודות");

    private String text;
    MainMenu(String text) { this.text = text; }
    public String getText() { return text; }
}