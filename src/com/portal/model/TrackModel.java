package com.portal.model;

import java.io.Serializable;

public class TrackModel implements Serializable{
	private String track_id;
	private String track_title;
	private String artist_id;
	private String artist_name;
	private String track_image_file;
	private String album_id;
	private String album_title;
	public String getTrack_id() {
		return track_id;
	}
	public void setTrack_id(String track_id) {
		this.track_id = track_id;
	}
	public String getTrack_title() {
		return track_title;
	}
	public void setTrack_title(String track_title) {
		this.track_title = track_title;
	}
	public String getArtist_id() {
		return artist_id;
	}
	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getTrack_image_file() {
		return track_image_file;
	}
	public void setTrack_image_file(String track_image_file) {
		this.track_image_file = track_image_file;
	}
	public String getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}
	public String getAlbum_title() {
		return album_title;
	}
	public void setAlbum_title(String album_title) {
		this.album_title = album_title;
	}
	@Override
	public String toString() {
		return "TrackModel [track_id=" + track_id + ", track_title="
				+ track_title + ", artist_id=" + artist_id + ", artist_name="
				+ artist_name + ", track_image_file=" + track_image_file
				+ ", album_id=" + album_id + ", album_title=" + album_title
				+ "]";
	}


}
