package it.epicode.data;

public abstract class ElementoCatalogo {
    protected Long ISBN;
    protected String title;
    protected String releaseDate;
    protected int pages;

    public ElementoCatalogo(Long ISBN, String title, String releaseDate, int pages) {
        this.ISBN = ISBN;
        this.title = title;
        this.releaseDate = releaseDate;
        this.pages = pages;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "ElementoCatalogo{" + "ISBN=" + ISBN + ", title='" + title + '\'' + ", releaseDate='" + releaseDate + '\'' + ", pages=" + pages + '}';
    }
}
