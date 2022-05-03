# RotoscopingProject Log
-David Blackstone
4/13/22
------------------
-  Java Program that turns real images into cartoon ones
-  -Supports Image Filtering
-  -Supports Video Filtering
-  - ("Heathers" Rotoscoped Movie Example:
    https://drive.google.com/file/d/1iX_9WgQZliuqXPkaw9ics_D6gnq2TAv9/view?usp=sharing )
   - ("City Demo" Rotoscoped video Example:
    https://drive.google.com/file/d/1iX_9WgQZliuqXPkaw9ics_D6gnq2TAv9/view?usp=sharing )
-  -Supports Condor Distributed Processing
- steps:
-   -GrayScale
-   -GaussianBlur
-   -EdgeDetection
-   -EdgeDirectionDetection
-   -EdgeThinning
-   -DoubleThresholding
-   -Hysterisis
-   -EdgeThickening
-   -Turn to full Black full White
-   Pallettization:
-   -K-means clustering
- Combine both images to create the final result
- *Scripts*
- -Split videos into batches
- -Create Condor Job file
- -Merge Audio/video

- Before:
![duck](https://user-images.githubusercontent.com/62959991/163188929-a5bba28f-6e2b-4eac-a600-b2bfa0b66c6d.png)
- After:
![sComplete](https://user-images.githubusercontent.com/62959991/163188986-8545f79e-57fb-4f1a-9c5e-b1e633960f65.png)

- Before:
![fight](https://user-images.githubusercontent.com/62959991/163189407-94b18b31-cf70-4b38-9aa9-59cd4e9cfe60.png)
- After:
![applesComplete](https://user-images.githubusercontent.com/62959991/163189475-054b2e1f-f45a-4645-946d-747ecf6a4800.png)

- More Examples:
![dials_canny](https://user-images.githubusercontent.com/62959991/166494487-b2455541-5c58-4332-9f27-67ea5684bca1.png)
![parachute_canny](https://user-images.githubusercontent.com/62959991/166494517-ee85b989-4eb8-4629-acb4-8f7d4ef2e351.png)
![city_canny](https://user-images.githubusercontent.com/62959991/166494541-52ca1a83-5753-416e-9096-2959c1689da7.png)
![lizard_canny](https://user-images.githubusercontent.com/62959991/166494552-3d44f9b5-d237-4ea2-bb64-132700af18ea.png)


