import moviepy.editor as mpe

video = mpe.VideoFileClip(r"C:\Users\daveb\Desktop\py\Heathers_Rotoscoped.mp4")
final_clip = video.set_audio(mpe.AudioFileClip(r"C:\Users\daveb\Desktop\py\audio.mp4"))
final_clip.write_videofile("heathers_Complete_Rotoscoped.mp4")
