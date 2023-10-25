package com.example.appremedio

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.appremedio.databinding.ActivityMainBinding
import com.example.appremedio.db.InstanceDb

import com.example.appremedio.db.PopulateDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding
    private lateinit var menuVar: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Instancia a db
        InstanceDb.build(applicationContext)
        //Popular a db metodo assincrono
        CoroutineScope(Dispatchers.IO).launch {
            if (InstanceDb.INSTANCE.PessoaDao().contarPessoas() == 0){
                PopulateDb.run()
            }
            InstanceDb.INSTANCE.ConsumoDao().deletarTodosConsumo()
            InstanceDb.INSTANCE.PessoaRemedioDao().deletarTodosPessoaRemedio()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            navController.navigate(R.id.NovoRemedioPessoaViewFragment)
            menuVar.clear()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        menuVar = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.navigate(R.id.NovoRemedioViewFragment)
        menuVar.clear()
        return when (item.itemId) {
            R.id.action_remedio -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}