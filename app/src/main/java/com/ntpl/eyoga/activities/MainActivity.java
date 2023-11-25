package com.ntpl.eyoga.activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.ntpl.eyoga.R;
import com.ntpl.eyoga.font.ButtonSemiBold;
import com.ntpl.eyoga.font.TextViewMedium;
import com.ntpl.eyoga.font.TextViewSemiBold;
import com.ntpl.eyoga.helper.SQLiteHandlerDisease;
import com.ntpl.eyoga.helper.SessionManager;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ButtonSemiBold clickHere;
    SessionManager sessionManager;
    String fullName, sessionEmail;
    SQLiteHandlerDisease db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new SQLiteHandlerDisease(this);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetails();
        fullName = user.get(SessionManager.KEY_FULL_NAME);
        sessionEmail = user.get(SessionManager.KEY_EMAIL_ID);

        clickHere = findViewById(R.id.clickHere);

        clickHere.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SearchDiseaseActivity.class);
            startActivity(intent);
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextViewSemiBold name = header.findViewById(R.id.name);
        TextViewMedium emailID =  header.findViewById(R.id.emailID);

        name.setText(fullName);
        emailID.setText(sessionEmail);


        db.deleteData();

        db.insertDisease("Thyroid", "Halasana","zOZumZ_lD1c","1. Eat at least 5 portions of a variety of fruit and vegetables every day\n\n" +
                        "2. Base meals on higher fibre starchy foods like potatoes, bread, rice or pasta\n\n" +
                        "3. have some dairy or dairy alternatives (such as soya drinks)\n\n" +
                        "4. eat some beans, pulses, fish, eggs, meat and other protein\n\n" +
                        "5. Choose unsaturated oils and spreads, and eat them in small amounts\n\n" +
                        "6. drink plenty of fluids (at least 6 to 8 glasses a day)",
                "1. Lie flat on the floor keeping the arms by the side of the body. Palms facing downward and legs together. Relax the body taking a few deep and slow breaths.\n\n" +
                        "2. Using the strength of your abdominal muscles, slowly lift the legs off the ground until they are perpendicular to the floor. Keep the legs straight and together.\n\n" +
                        "3. Gently press your arms against the floor and raise your buttocks. Continue to roll the spine till your big toes reach the ground over your head. Don’t force your feet to touch the ground. Keep the legs straight.\n\n" +
                        "4. Try to keep the spine as straight as possible. Ideally, the spine is perpendicular to the floor in the final position. A beginner can take the support of arms by placing the hands behind the ribcage to support the back.\n\n" +
                        "5. Bring your arms closer and interlock the fingers of both of your hands. If this feels tough, simply keep the arms closer and join the thumps.\n\n" +
                        "6. In the final position, the chin is tucked in the center of collar bones. However, it takes time to achieve this position. Don’t force yourself into it as that may strain the muscles of your neck.\n\n" +
                        "7. Stretch the legs and arms in the opposite direction. Hold the pose for around 15 seconds to a minute depending on how long you feel comfortable. Take slow and deep breaths.\n\n" +
                        "8. To release the pose, gently lower the spine and bring the legs in vertical position. Slowly lower the legs to the ground. Relax the whole body. \n\n\n\n" +
                        "Practice once; it cab be practiced for 3-5 minutes but or beginners only a few seconds are good.",
                "1. Modulates the functioning of all internal organs therefore keeps them healthy.\n\n" +
                        "2. Aids digestions, alleviate constipation and dyspepsia.\n\n" +
                        "3. Invigorate immune system.\n\n" +
                        "4. Rejuvenate adrenal glands and spleen.\n\n" +
                        "5. Improves the functioning of pancreas, liver and kidney.\n\n" +
                        "6. Beneficial for the organs of the female reproductive system.\n\n" +
                        "7. Regulates the production of insulin thus prevents diabetes.\n\n" +
                        "8. Enhances blood circulation in the spinal column and tones spinal nerves.\n\n" +
                        "9. Increase metabolic rate by regulating the functioning of thyroid gland.\n\n" +
                        "10. Makes abdominal muscles stronger and reduces spasms in back muscles.\n\n" +
                        "11. Improves the flexibility of shoulders, elbows and wrists.","It's recommended you avoid Halasan, and probably Shoulder Stand, if you have any of the following:\n\n" +
                        "\n\n" +
                        "1. Diarrhea or menstrual cramps\n\n" +
                        "2. An injury to the neck, cervical vertebrae or shoulders\n\n" +
                        "3. High blood pressure, as the pose naturally increases blood pressure\n\n" +
                        "4. Glaucoma", "","","");

        db.insertDisease("Arthritis", "Shishuasana","QuxSnyKETHg","1. Fatty Fish\n2. Garlic\n3. Ginger\n4. Broccoli\n5. Walnuts\n6. Berries\n7. Spinach\n8. Grapes",
                "1. Sit on your heels. Keeping your hips on the heels, bend forward, and lower your forehead to the floor.\n\n" +
                        "2. Keep the arms alongside your body with hands on the floor, palms facing up. (If this is not comfortable, you can place one fist on top of another and rest your forehead on them.)\n\n" +
                        "3. Gently press your chest on the thighs.\n\n" +
                        "4. Hold.\n\n" +
                        "5. Slowly come up to sit on the heels, uncurling vertebra by vertebra and relax.",
                "1. Alleviates any pain in the neck, head, or shoulders\n\n" +
                        "2. Prevents and reduces feelings of anxiety and depression\n\n" +
                        "3. Excellent pose to reduce stress\n\n" +
                        "4. Helps to prevent and treat constipation\n\n" +
                        "5. Induces relaxation in the spine\n\n" +
                        "6. Eases and opens up the hips\n\n" +
                        "7. Has a calming effect on the nervous system","Shishuasana helps in providing relief from stress and neck pain but one needs to be cautious. Those having knee injuries or those who are pregnant should avoid this asana. It is always best to practise it under the guidance of a trained yoga instructor.", "","","");
        db.insertDisease("Lower Back Pain", "Supta Matyendrasana","ezyMaQEaVaI","Kale, spinach, and broccoli are all list-toppers for an anti-inflammatory diet with back-pain-fighting properties. Other good food choices for an anti-pain diet: avocados; nuts (walnuts, almonds, pecans, and Brazil nuts); lean proteins, such as chicken and turkey; beans; and cocoa.",
                "1. Lie down on your back.\n\n" +
                        "2. Bend your knees and put the soles of your feet on the floor with your knees pointing up toward the ceiling.\n\n" +
                        "3. Press into your feet to lift your hips slightly off the floor and shift them about an inch to your right. This is an important step because it sets your hips up to stack one on top of the other when you move into the twist. \n\n" +
                        "4. Exhale and draw your right knee into your chest and extend your left leg flat on the floor. Keep your left foot actively flexed throughout the pose. Inhale.\n\n" +
                        "5. Exhale and cross your right knee over your midline to the floor on the left side of your body. Your right hip is now stacked on top of your left hip. You can hook your right foot behind your left knee if you like.\n\n" +
                        "6. Open your right arm to the right, keeping it in line with your shoulders. Rest your left hand on your right knee or extend it to make a T shape with the arms. Turn your palms toward the ceiling.\n\n" +
                        "7. Turn your head to the right, bringing your gaze over your shoulder to your right fingertips. You can skip this step if it doesn't feel good on your neck.\n\n" +
                        "8. On your exhalations, release your left knee and your right shoulder toward the floor.\n\n" +
                        "9. Hold the pose for five to 10 breaths. To come out of the pose, inhale and roll onto your back, drawing your right knee into your chest. Release both legs to the floor to neutralize your spine for several breaths before doing the other side.",
                "Supta Matsyendrasana stretches the glutes, chest, and obliques. Because of the chest stretch, it is considered a heart opener. It improves spinal mobility and can aid digestion. It is a relaxing pose at the end of a yoga session. In everyday life, your posture will benefit from this antidote to sitting and hunching over work.","Avoid these errors when doing this pose.\n\n1. Holding the Breath\n\n2. Forcing Knee to the Floor\n\n", "","","");
        db.insertDisease("PCOS (Polycystic Ovarian Syndrome)", "Dhanurasana","4P2mYcOGxbU",
                "Great options for high-fiber foods include:\n\n" +
                "1. cruciferous vegetables, such as broccoli, cauliflower, and Brussels sprouts\n\n" +
                "2. greens, including red leaf lettuce and arugula\n\n" +
                "3. green and red peppers\n\n" +
                "4. beans and lentils\n\n" +
                "5. almonds\n\n" +
                "6. berries\n\n" +
                "7. sweet potatoes\n\n" +
                "8. winter squash\n\n" +
                "9. pumpkin",
                "1. Lie on your stomach with your feet apart, in line with your hips, and your arms by the side of your body.\n\n" +
                        "2. Fold your knees, take your hands backward, and hold your ankles.\n\n" +
                        "3. Breathe in, and lift your chest off the ground and pull your legs up and towards the back. \n\n" +
                        "4. Look straight ahead with a smile on your face.\n\n" +
                        "5. Keep the pose stable while paying attention to your breath. Your body is now curved and as taut as a bow.\n\n" +
                        "6. Continue to take long, deep breaths as you relax in this pose. But, bend only as far as your body permits you to. Do not overdo the stretch.\n\n" +
                        "7. After 15 -20 seconds, as you exhale, gently bring your legs and chest to the ground. Release the ankles and relax.","1. Stretches the entire front of the body, ankles, thighs and groins, abdomen and chest, and throat, and deep hip flexors (psoas)\n\n" +
                "2. Strengthens the back muscles.\n\n" +
                "3. Improves posture.\n\n" +
                "4. Stimulates the organs of the abdomen and neck.","1. Do not practice this asana if you have neck or lower back injuries.\n\n" +
                "2. Do not practice this asana if you have undergone any abdominal surgery recently.\n\n" +
                "3. Avoid this asana if you have problems like high blood pressure, ulcers, migraine, headache or hernia.", "","","");
        db.insertDisease("Diabetes", "Ardha Mutrendrasana","51EqCa6ZGCw","1. vegetables. nonstarchy: includes broccoli, carrots, greens, peppers, and tomatoes. ...\n\n" +
                "2. fruits—includes oranges, melon, berries, apples, bananas, and grapes.\n\n" +
                "3. grains—at least half of your grains for the day should be whole grains. ...\n\n" +
                "4. protein. ...\n\n" +
                "5. dairy—nonfat or low fat.",
                "1. Sit up with the legs stretched out straight in front of you, keeping the feet together and the spine erect.\n\n" +
                        "2. Bend the left leg and place the heal of the left foot beside the right hip (optionally, you can keep the left leg straight).\n\n" +
                        "3. Take the right leg over the left knee.\n\n" +
                        "4. Place the left hand on the right knee and the right hand behind you.\n\n" +
                        "5. Twist the waist, shoulders and neck in this sequence to the right and look over the right shoulder.\n\n" +
                        "6. Keep the spine erect.\n\n" +
                        "7. Hold and continue with gentle long breaths in and out.\n\n" +
                        "8. Breathing out, release the right hand first (the hand behind you), release the waist, then chest,lastly the neck and sit up relaxed yet straight.\n\n" +
                        "9. Repeat to the other side.\n\n" +
                        "10. Breathing out, come back to the front and relax.","1. It strengthens and tones your obliques and abs.\n\n" +
                "2. The pose energizes and stretches the spine.\n\n" +
                "3. Open your shoulders, hips and neck.\n\n" +
                "4. Increase the flexibility in your spine and hips.\n\n" +
                "5. It also cleanses your internal organs.\n\n" +
                "6. It will improves digestion as well as elimination of the wastes.","1. Should be avoided during pregnancy and menstruation due to the strong twist in the abdomen.\n\n" +
                "2. People with Heart, abdominal or brain surgeries should not practice this asana.\n\n" +
                "3. Care should be taken for those with peptic ulcer or hernia.\n\n" +
                "4. Those with severe spinal problems should avoid and those with mild slipped disc can benefit but in severe cases it should be avoided.", "","","");
        db.insertDisease("Stomach Disorder", "Paschimottanasana","T8sgVyF4Ux4","1. Foods containing ginger. Ginger is a plant that can reduce bloating and other digestive problems. ...\n\n" +
                "2. Unsaturated fats. This type of fat helps the body absorb vitamins. ...\n\n" +
                "3. Vegetables with skin. Vegetables are rich in fiber, which is an important nutrient for digestion. ...\n\n" +
                "4. Fruits. ...\n\n" +
                "5. Whole-grain foods. ...\n\n" +
                "6. Yogurt. ...\n\n" +
                "7. Kefir. ...\n\n" +
                "8. Leafy green vegetables.",
                "1. Bring your arms straight out to the sides and up over your head, reaching toward the ceiling.\n\n" +
                        "2. Inhale and draw your spine up long.\n\n" +
                        "3. As you exhale, begin to come forward, hinging at your hips. Imagine your pelvis as a bowl of water that is tipping forward.\n\n" +
                        "4. On each inhale, lengthen your spine. You may come a bit out of your forward bend to do this.\n\n" +
                        "5. On each exhale, deepen into your forward bend. Imagine your belly coming to rest on your thighs, rather than your nose coming to your knees. This will help you keep your spine long.\n\n" +
                        "6. Keep the neck as the natural extension of your spine, neither cranking it to look up nor letting it go completely.\n\n" +
                        "7. When you have come to your full extension with the spine long, decide whether you want to stay here or let your spine round forward.\n\n" +
                        "8. Take hold of your ankles or shins, whichever you can reach. You can also use a strap around your feet. Keep your feet flexed strongly throughout.","1. Calms the brain and helps relieve stress and mild depression.\n\n" +
                "2. Stretches the spine, shoulders, hamstrings.\n\n" +
                "3. Stimulates the liver, kidneys, ovaries, and uterus.\n\n" +
                "4. Improves digestion.\n\n" +
                "5. Helps relieve the symptoms of menopause and menstrual discomfort.\n\n" +
                "6. Soothes headache and anxiety and reduces fatigue.","The most obvious effect of Paschimottanasana is that it stretches the back of the leg. Tight hamstrings can often lead to a hunched, rounded posture and could be an indirect cause of back injury. If the muscles of the leg aren't sufficiently elastic, it can also put a strain on the knee and hip joint.", "","","");
        db.insertDisease("Migraine", "Padmasana","UTOBheDjLhQ","1. orange, yellow, and green vegetables, such as summer squash, sweet potatoes, carrots, and spinach.\n\n" +
                "2. carbonated, spring, or tap water.\n\n" +
                "3. rice, especially brown rice.\n\n" +
                "4. dried or cooked fruits, particularly non-citrus kinds such as cherries and cranberries.",
                "1. Sit on the floor or on a mat with legs stretched out in front of you while keeping the spine erect.\n\n" +
                        "2. Bend the right knee and place it on the left thigh. Make sure that the sole of the feet point upward and the heel is close to the abdomen.\n\n" +
                        "3. Now, repeat the same step with the other leg.\n\n" +
                        "4. With both the legs crossed and feet placed on opposite thighs, place your hands on the knees in mudra position.\n\n" +
                        "5. Keep the head straight and spine erect.\n\n" +
                        "6. Hold and continue with gentle long breaths in and out.",
                "1. Opens up the hips.\n\n" +
                "2. Stretches the ankles and knees.\n\n" +
                "3. Calms the brain.\n\n" +
                "4. Increases awareness and attentiveness.\n\n" +
                "5. Keeps the spine straight.\n\n" +
                "6. Helps develop good posture.\n\n" +
                "7. Eases menstrual discomfort and sciatica.\n\n" +
                "8. Helps keeps joints and ligaments flexible.","Patients suffering from below mentioned conditions should avoid doing Padmasana - \n\n1. Knee Injury\n\n2. Ankle Injury", "","","");

        db.insertDisease("Liver problem", "Ardha Bhekasana","giGl3EpvP68","Here are a few foods to include in your healthy liver diet: Coffee to lower abnormal liver enzymes. Greens to prevent fat buildup. Tofu to reduce fat buildup. Fish for inflammation and fat levels. Oatmeal for energy. Walnuts to improve the liver. Avocado to help protect the liver.",
                "1 To start this asana get onto the yoga mat and lie down on your stomach. Your elbow should place below the shoulders. \n\n" +
                        "2. Now, solid your pubic bone has downed along pull back your belly in.\n\n" +
                        "3. Then, cross your left lower arm before the body.\n\n" +
                        "4. At that point, twist your right knee towards your back and bring your right hand and try to grab the top of the right foot. As you doing this, tenderly pulls your foot towards the outside of your right hip zone.\n\n" +
                        "5. Attempt to mellow the top of the right thigh, turn your fingers towards the front, if your body is allowed.\n\n" +
                        "6. At this point, try to turn your right side body in the forward direction and you should look forward. \n\n" +
                        "7. At this point, breathe normally\n\n" +
                        "8. Hold this position as much as your body is comfortable. \n\n" +
                        "9. For beginners, try to be in this position for 5 to 6 breath.\n\n" +
                        "10. To release from this position, slowly get back to the starting position, keep your leg straight and take a deep breath and relax. Keep both the hands alongside your body. \n\n" +
                        "11. Now you can again do this asana with opposite leg. You should do minimum 10 repetitions of this asana for better result.\n\n" +
                        "12. Initially, when you start this yoga asana, you feel some pain in your legs, hip, thigh, shoulder, abdomen and knee, but as you practice more you can do this asana more easily.",
                "1. Provides stretch to the entire front of the body.\n\n" +
                        "2. Stretches the ankles, thighs, quadriceps and groins and also deep hip flexors.\n\n" +
                        "3. Strengthens calves, ankles and hamstrings.\n\n" +
                        "4. Stretches the abdomen, chest and throat.\n\n" +
                        "5. Stretches the back muscles and improves the flexibility of the back.",
                "Patients suffering from below mentioned conditions should avoid doing Bhekasana-\n\n1. High blood pressure\n\n2.Low blood pressure\n\n3. Rotator cuff injuries\n\n4. Insomnia", "","","");

        db.insertDisease("Depression", "Baddha Konasana","hRcvSEtoecg","1. Beta-carotene: apricots, broccoli, cantaloupe, carrots, collards, peaches, pumpkin, spinach, sweet potato\n\n" +
                "2. Vitamin C: blueberries, broccoli, grapefruit, kiwi, oranges, peppers, potatoes, strawberries, tomato\n\n" +
                "3. Vitamin E: margarine, nuts and seeds, vegetable oils, wheat germ",
                "1. Sit up straight with your legs stretched out and keep your feet together.\n\n" +
                        "2. Exhale and bend your knees to bring your heels close to your pelvis. Press the soles of your feet together. Spread your knees as comfortably as you can and push your outer thighs towards the floor.\n\n" +
                        "*Sit on a folded blanket or a cushion, if you are not able to spread your knees or bring your heels too close to your pelvis.\n\n" +
                        "3. Keep the outer edges of your feet on the floor and slightly turn your big toes towards the ceiling. Make sure your soles are pressed against each other.\n\n" +
                        "4. Grasp your big toes with your index finger, middle finger and your thumbs.\n\n" +
                        "*Hold your ankles or your shin bones, if you are not able to hold your big toes.\n\n" +
                        "5. Inhale and lift your torso up, lengthen your back, keep your elbows straight and gaze forward.\n\n" +
                        "6. Exhale and slowly push your chest towards the floor. Try to bring your chin or your forehead on the floor.\n\n" +
                        "*Do not force your body in any way. If you are not able to bring your forehead down to the floor, then remain wherever you are comfortable.\n\n" +
                        "7. Stay in this pose for 6 long breaths (or at least for 60 seconds)."
                ,"1. Stimulates abdominal organs, ovaries and prostate gland, bladder, and kidneys.\n\n" +
                "2. Stimulates the heart and improves general circulation.\n\n" +
                "3. Stretches the inner thighs, groins, and knees.\n\n" +
                "4. Helps relieve mild depression, anxiety, and fatigue.\n\n" +
                "5. Soothes menstrual discomfort and sciatica.\n\n" +
                "6. Helps relieve the symptoms of menopause.",
                "1. This pose can worsen Sciatica conditions. People suffering from Sciatica, can do this by placing a cushion below the hips. While doing this asana person must be aware of backward rotation of hips.\n" +
                        "\n" +
                        "2. If you have any sort of lower back disorder and you have been advised not to stretch your flexion and spine, you should keep your back as straight as possible so as to less impact your spine.\n" +
                        "\n" +
                        "3. If you have ever encountered a whiplash or similar neck injuries, you must avoid dropping your head down or move it side-wise.", "","","");

        db.insertDisease("Kidney", "Salamba Bhujangasana","fOdrW7nf9gw","With all meal plans, including the kidney-friendly diet, you need to track how much of certain nutrients you take in, such as:\n\n" +
                "\n\n" +
                "Calories\n\n" +
                "Protein\n\n" +
                "Fat\n\n" +
                "Carbohydrates","1. Lie on your stomach with your toes flat on the floor and forehead resting on the ground.\n\n" +
                        "2. Keep your legs close together, with your feet and heels lightly touching each other.\n\n" +
                        "3. Stretch your hands in front of you with palms facing downward and arms touching the ground.\n\n" +
                        "4. Taking a deep breath in, slowly lift your head, chest and abdomen while keeping your navel on the floor.\n\n" +
                        "5. Pull your torso back and off the floor with support of your arms.\n\n" +
                        "6. Keep breathing with awareness, as you curve your spine vertebra by vertebra.\n\n" +
                        "7. Ensure that your feet are still close together and head facing straight ahead.\n\n" +
                        "8. Breathing out, gently bring down your abdomen, chest and head back to the floor.",
                "It strengthens various areas of your body, such as the back, spine, chest, shoulders, abdominal muscles, neck and arms. It also tones your body and helps to develop strong muscles. The tightening of the glute muscles provides support to the hip, spine and pelvis.","Patients suffering from below mentioned conditions should avoid doing Bhujangasana\n\n1. Back injuries\n\n2. Fracture of ribs and wrist\n\n3. Hernia, after surgery for hernia\n\n4. Headaches\n\n5. Pregnancy", "","","");

        db.insertDisease("High BP", "Uttanasana","0KzyEgkq7zw","1. Citrus fruits\n2. Salmon and other fatty fish\n3. Swiss chard\n4. Pumpkin seeds\n5. Beans and lentils \n6. Berries",
                "1. Stand straight on your mat, and rest your hands on your hips. Inhale.\n\n" +
                        "2. Exhale and gently soften your knees and bend forward, folding from your hips. You need to counterbalance the weight of your body. To do this, you must move your hips and tailbone slightly back as the rest of your body moves forward.\n\n"+
                "3. Remember to keep your knees soft as you do all of this. This will allow your buttocks to point up and your hips to move forward into the upper thighs.\n\n" +
                        "4. Let your hands rest on the ground, next to your feet. Your feet must be parallel to each other, and your second and middle toes must point forward. Let your chest float over your feet. Widen the space between your chest bone and pubis. Feel the fold and the stretch from your hip bone. If you feel it from the rounding of your lower back, you are doing something wrong.\n\n" +
                        "5. You must feel a stretch in your hamstrings as well, and if you are not yet feeling it, extend your knees a little more.\n\n" +
                        "6. Turn your thighs inward, and root yourself into your heels. This will allow better alignment.\n\n" +
                        "7. Your head must be left to dangle, such that the crown reaches the floor. Look through your legs, and hold the pose.\n\n" +
                        "8. When you wish to release the pose, contract the core and the abdomen muscles. Inhale and place your hand on the hips. Rise slowly, ensuring there is an elongation in your back. Let there be a distance between your pubis and your chest bone. Slowly stand up.",
                "1. Calms the brain and helps relieve stress and mild depression.\n\n" +
                "2. Stimulates the liver and kidneys.\n\n" +
                "3. Stretches the hamstrings, calves, and hips.\n\n" +
                "4. Strengthens the thighs and knees.\n\n" +
                "5. Improves digestion.\n\n" +
                "6. Helps relieve the symptoms of menopause.\n\n" +
                "7. Reduces fatigue and anxiety.\n\n" +
                "8. Relieves headache and insomnia.",
                "If you suffer from any of this condition avoid practicing Uttanasana: spine injury, pain in ankles or hip joints, knee problems, sciatica, heart problems, severe back pain or an abdominal hernia.", "","","");
        db.insertDisease("Alzheimers", "Kapalbati","Q8KBsSXvhJ8","1. Leafy green vegetables, at least 6 servings/week.\n\n" +
                "2. Other vegetables, at least 1 serving/day.\n\n" +
                "3. Berries, at least 2 servings/week.\n\n" +
                "4. Whole grains, at least 3 servings/day.\n\n" +
                "5. Fish, 1 serving/week.\n\n" +
                "6. Poultry, 2 servings/week.\n\n" +
                "7. Beans, 3 servings/week.\n\n" +
                "8. Nuts, 5 servings/week.","1. Close the eyes and relax the whole body.\n\n" +
                "2. Inhale deeply through both nostrils, expand the chest.\n\n" +
                "3. Expel the breath with forceful contractions of the abdominal muscles and relax.\n\n" +
                "4. Do not strain.\n\n" +
                "5. Continue active/forceful exhalation and passive inhalation.\n\n" +
                "6. Complete 30 rapid breaths, then take a deep breath and exhale slowly.\n\n" +
                "7. This is one round of Kapalabhati.\n\n" +
                "8. Each round shall be followed by deep breathing.\n\n" +
                "9. Repeat 2 more rounds.","It improves blood circulation and digestion. Kapalbhati Prayanama benefits from weight loss as well. This is possible as it increases your metabolic rate rapidly. The kriya stimulates the internal organs, especially the abdominal ones, and therefore, helps people with diabetes","It can lead to heart problems, high blood pressure, vertigo, hernia, epilepsy and related brain problems", "","","");
        db.insertDisease("Joint Pain", "Setu Bandha Sarvangasana","g78vfuC4QBI","1. Omega-3 Fatty Acids / Fish Oils. Cold-water fish are a terrific source of Omega-3s fatty acids, which are essential nutrients for human health. ...\n\n" +
                "2. Nuts and Seeds. There's good news for the vegans and vegetarians among us. ...\n\n" +
                "3. Brassica Vegetables. ...\n\n" +
                "4. Colorful Fruits. ...\n\n" +
                "5. Olive Oil. ...\n\n" +
                "6. Lentils and Beans. ...\n\n" +
                "7. Garlic and Root Vegetables. ...\n\n" +
                "8. Whole Grains.",
                "1. Begin the asana by lying flat on your back.\n\n" +
                        "2. Bend your knees and place your feet on the floor hip-width apart. Make sure that your ankles and knees are placed in a straight line.\n\n"+
                        "3. Let your arms rest beside your body, with your palms facing downwards.\n\n" +
                        "4. Inhale, and lift your back (lower, upper, and middle) off the floor. Roll in your shoulders, and make sure your chin touches your chest without you having to move it. Let your shoulders, feet, and arms support your weight.\n\n" +
                        "5. Firm up your buttocks as you tighten them. Make sure your thighs are parallel to each other and the floor.\n\n" +
                        "6. Interlace your fingers and push your hands harder to the ground to lift your torso higher.\n\n" +
                        "7. Hold the posture for at least a minute. Breathe slowly and deeply.\n\n" +
                        "8. Exhale and release the pose",
                "1. Stretches the chest, neck, spine, and hips.\n\n" +
                "2. Strengthens the back, buttocks, and hamstrings.\n\n" +
                "3. Improves circulation of blood.\n\n" +
                "4. Helps alleviate stress and mild depression.\n\n" +
                "5. Calms the brain and central nervous system.\n\n" +
                "6. Stimulates the lungs, thyroid glands, and abdominal organs.\n\n" +
                "7. Improves digestion.","Bridge pose (Setu Bandhasana) precautions · One shouldn't perform this pose if suffering from neck pain. · In back injury, it should be avoided.", "","","");
        db.insertDisease("Pregnancy", "Tadasana","ET_cKo1Ta1s","1. Dairy products. ...\n\n" +
                "2. Legumes. ...\n\n" +
                "3. Sweet potatoes. ...\n\n" +
                "4. Salmon. ...\n\n" +
                "5. Eggs. ...\n\n" +
                "6. Broccoli and dark, leafy greens. ...\n\n" +
                "7. Lean meat and proteins. ...\n\n" +
                "8. Berries.","1. Stand straight on the ground, and take a small gap between your feet.\n\n" +
                "2. With deeply breathing (inhale), raise your both arms.\n\n" +
                "3. Keep your arms upward by interlocking your fingers.\n\n" +
                "4. Now come on the toes by raising your heels simultaneously.\n\n"+
                "5. Feel the pressure of stretching from toes to fingers.\n\n" +
                "6. Try to maintain this pose as long as you can with slow and deep breathing.\n\n" +
                "7. Now come to the original position with deep breathing (exhale).\n\n" +
                "8. You can perform the number of rounds as per your convenience after having relaxation for a while.","The biggest benefit of tadasana is that it helps in correcting your posture and improves your balance by making your spine more agile.\n\n2. It helps in increasing the flexibility of your ankles, thighs and joints","Tadasana shall be avoided - \n\n1. Low blood pressure\n\n2. Sleeplessness\n\n3. Headache and Migraine", "","","");

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_actiity_tracker) {
            Intent intent = new Intent(MainActivity.this, ActivityTrackerActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_diet_planner) {

            Intent intent = new Intent(MainActivity.this, DietPlannerActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_logout) {

            final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
            builder.setMessage("Are you sure you want to logout ?")
                    .setCancelable(false)
                    .setPositiveButton("Logout", (dialog, id1) -> {
                        dialog.cancel();

                        sessionManager.logoutUser();

                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        // Closing all the Activities
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        // Staring Login Activity
                        startActivity(i);
                        finish();

                    })
                    .setNegativeButton("No", (dialog, id12) -> dialog.cancel());
            final AlertDialog alert = builder.create();
            alert.show();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setMessage("Are you sure you want to exit ?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                    homeIntent.addCategory( Intent.CATEGORY_HOME );
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                })
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        final AlertDialog alert = builder.create();
        alert.show();

    }
}