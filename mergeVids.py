from moviepy.editor import VideoFileClip, concatenate_videoclips

videos = []
for x in range(42):
    video = VideoFileClip("G:\Downloads\movies\output" +str(x+1) +".mp4")
    print(str(x+1) +" found")
    videos.append(video)

final_video= concatenate_videoclips(videos)
final_video.write_videofile("Heathers_Rotoscoped.mp4")