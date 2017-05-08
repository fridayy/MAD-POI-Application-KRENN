package ninja.harmless.mad.ui

class MainActivity : android.support.v7.app.AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ninja.harmless.mad.R.layout.activity_main)
    }

    fun switchToListActivity(view: android.view.View?) {
        val intent = android.content.Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    fun switchToAddActivity(view: android.view.View?) {
        val intent = android.content.Intent(this, AddActivity::class.java)
        startActivity(intent)
    }
}
