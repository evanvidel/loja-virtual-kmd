package com.axweb.lojavirtual

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import com.axweb.lojavirtual.databinding.ActivityTelaPrincipalBinding
import com.axweb.lojavirtual.form.FormLoginActivity
import com.axweb.lojavirtual.fragments.ProdutosFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.app_bar_tela_principal.*

class TelaPrincipalActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityTelaPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarTelaPrincipal.toolbar)
        val produtosFragment = ProdutosFragment()
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.flame_container, produtosFragment)
        fragment.commit()


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val toggle = ActionBarDrawerToggle(
            this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)


    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_produtos) {

            val produtosFragment = ProdutosFragment()
            val fragment = supportFragmentManager.beginTransaction()
            fragment.replace(R.id.flame_container, produtosFragment)
            fragment.commit()

        }else if(id == R.id.nav_cadastrar_produtos) {

            val intent = Intent(this, CadastroProdutosActivity::class.java)
            startActivity(intent)

        }else if(id == R.id.nav_contato) {

        }
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tela_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut()
            backToFormLogin()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun backToFormLogin() {
        val intent = Intent(this, FormLoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}