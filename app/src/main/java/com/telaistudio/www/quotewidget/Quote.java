package com.telaistudio.www.quotewidget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Anton on 02.01.2017.
 */

public class Quote {

    private String author;
    private String quote;

    public Quote(@Nullable String author, @NonNull String quote) {
        this.quote = quote;
        this.author = author;
    }

    public Quote(@NonNull String quote) {
        this("QuoteWidget", quote);
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }
}
