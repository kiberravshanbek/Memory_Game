
import com.example.memorygame.R
import com.example.model.ImageModel

class AlImages {
    val images = ArrayList<ImageModel>()
    fun addWords(): ArrayList<ImageModel> {

        // shu yerga hamma rasmlarni qushib chiqmamiz
        // bu bizga rasmlar listini beradi
        images.add(ImageModel(R.drawable.img))
        images.add(ImageModel(R.drawable.img_4))

        return images
    }
}