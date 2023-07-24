package com.example.app;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {

    private String firstName;
    private String lastName;

    public HomePage() {
        // Create a form with two TextFields to collect first name and last name
        Form<Void> form = new Form<>("inputForm");

        TextField<String> firstNameField = new TextField<>("firstName", Model.of(""));
        form.add(firstNameField);

        TextField<String> lastNameField = new TextField<>("lastName", Model.of(""));
        form.add(lastNameField);

        // Add a submit button to the form
        form.add(new Button("submitButton") {
            @Override
            public void onSubmit() {
                // Process the user input here
                firstName = firstNameField.getModelObject();
                lastName = lastNameField.getModelObject();
            }
        });

        // Add a label to display the user input details
        Label detailsLabel = new Label("detailsLabel", Model.of(""));
        add(detailsLabel);

        // Display the user input details in the label when the form is submitted
        form.add(new AjaxEventBehavior("onsubmit") {
            @Override
            protected void onEvent(AjaxRequestTarget target) {
                String userDetails = "First Name: " + firstName + ", Last Name: " + lastName;
                detailsLabel.setDefaultModel(Model.of(userDetails));
                target.add(detailsLabel);
            }
        });

        add(form);
    }
}
