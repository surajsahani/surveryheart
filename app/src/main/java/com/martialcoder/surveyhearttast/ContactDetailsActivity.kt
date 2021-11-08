package com.martialcoder.surveyhearttast

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.martialcoder.surveyhearttast.data.Contact
import com.martialcoder.surveyhearttast.data.ContactDb
import com.martialcoder.surveyhearttast.data.DaoContact
import kotlinx.android.synthetic.main.activity_contact_details.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView


class ContactDetailsActivity : AppCompatActivity() {


    private var daoContact: DaoContact? = null
    private var viewModel: ContactListViewModel? = null

    private var currentContact: Int? = null
    private var contact: Contact? = null
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        var db: ContactDb = ContactDb.getDataBase(this)

        daoContact = db.daoContact()


        viewModel = ViewModelProviders.of(this).get(ContactListViewModel::class.java)
        currentContact = intent.getIntExtra("idContact", -1)
        if (currentContact != -1) {
            setTitle(R.string.edit_contact_title)
            contact = daoContact!!.getContactById(currentContact!!)
            name_edit_text.setText(contact!!.name)
            number_edit_text.setText(contact!!.number)
            //autocompleteTextView.setText(contact!!.type)

            business_contact.setText(contact!!.business)
            personal_contact.setText(contact!!.customer)
//            BusinessRadio.setText(contact!!.business)
//            customerRadio.setText(contact!!.customer)
        } else {
            setTitle(R.string.add_contact_title)
            invalidateOptionsMenu()
        }

        //autoCompleteTextView = findViewById(R.id.autocompleteTextView)

//        val colors = arrayOf(
//            "Business", "Personal",
//        )
//
//        val adapter = ArrayAdapter(
//            this,
//            android.R.layout.select_dialog_item,
//            colors
//        )
//
//
//        // Give the suggestion after 1 words.
//        autoCompleteTextView.setThreshold(1);
//
//        // Set the adapter for data as a list
//        autoCompleteTextView.setAdapter(adapter);
//        autoCompleteTextView.setTextColor(Color.BLACK);

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
                when (item!!.itemId) {
            R.id.done_item -> {
                if (currentContact == -1) {
                    saveContact()
                    Toast.makeText(this, getString(R.string.save_contact), Toast.LENGTH_SHORT).show()
                } else {
                    updateContact()
                    Toast.makeText(this, getString(R.string.update_contact), Toast.LENGTH_SHORT).show()
                }

                finish()
            }
            R.id.delete_item -> {
                deleteContact()
                Toast.makeText(this, getString(R.string.delete_contact), Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        if (currentContact == -1) {
            menu.findItem(R.id.delete_item).isVisible = false
        }
        return true
    }

    private fun saveContact() {
        var nameContact = name_edit_text.text.toString()
        var numberContact = number_edit_text.text.toString()
        var business = business_contact.text.toString()
        var customer = personal_contact.text.toString()
        //var type = autocompleteTextView.text.toString()
        var contact = Contact(0, nameContact, numberContact,business,customer )
        viewModel!!.addContact(contact)
    }

    private fun deleteContact() {
        daoContact!!.deleteContact(contact!!)
    }

    private fun updateContact() {
        var nameContact = name_edit_text.text.toString()
        var numberContact = number_edit_text.text.toString()
        var contact = Contact(contact!!.id, nameContact, numberContact)
        daoContact!!.updateContact(contact)
    }
}

