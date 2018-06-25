package ru.pristalovpavel.personal.friendlyjenkins.ui

import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.securepreferences.SecurePreferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.pristalovpavel.personal.friendlyjenkins.R
import ru.pristalovpavel.personal.friendlyjenkins.model.ViewResponse
import ru.pristalovpavel.personal.friendlyjenkins.repository.web.JenkinsService
import ru.pristalovpavel.personal.friendlyjenkins.ui.fragment.LoginFragment
import ru.pristalovpavel.personal.friendlyjenkins.ui.fragment.MainPageFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, LoginFragment.OnLoginInteractionListener {

    override fun onLoginInteraction() {
        val prefs = SecurePreferences(this)
        val login = prefs.getString(LoginFragment.LOGIN, "")
        val password = prefs.getString(LoginFragment.PASSWORD, "")

        if(!login.isBlank() && !password.isBlank()) {
            supportFragmentManager.beginTransaction().replace(R.id.container, MainPageFragment.newInstance()).commit()
        }
    }

    private val jenkinsService by lazy { JenkinsService.instance }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            jenkinsService.viewList("RZD").enqueue(object : Callback<ViewResponse> {
                override fun onResponse(call: Call<ViewResponse>?, response: Response<ViewResponse>?) {
                    Snackbar.make(view, "Deal with it!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }

                override fun onFailure(call: Call<ViewResponse>?, t: Throwable?) {
                    Snackbar.make(view, "Some error:/ Sorry.", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
            })

        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, LoginFragment.newInstance()).commit()
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
