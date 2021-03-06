/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package edu.umbc.idynin1.avro;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Tweet extends org.apache.avro.specific.SpecificRecordBase implements
		org.apache.avro.specific.SpecificRecord {
	public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser()
			.parse("{\"type\":\"record\",\"name\":\"Tweet\",\"namespace\":\"edu.umbc.idynin1\",\"fields\":[{\"name\":\"text\",\"type\":\"string\",\"doc\":\"Tweet text\"},{\"name\":\"id\",\"type\":\"long\",\"doc\":\"Tweet id\"},{\"name\":\"symbols\",\"type\":\"string\",\"doc\":\"Entities: symbols\"},{\"name\":\"urls\",\"type\":\"string\",\"doc\":\"Entities: urls\"},{\"name\":\"hashtags\",\"type\":\"string\",\"doc\":\"Entities: hashtags\"},{\"name\":\"user_mentions\",\"type\":\"string\",\"doc\":\"Entities: user mentions\"},{\"name\":\"longitude\",\"type\":\"float\",\"doc\":\"Geographic Coordinate: longitude\"},{\"name\":\"latitude\",\"type\":\"float\",\"doc\":\"Geographic Coordinate: latitude\"},{\"name\":\"username\",\"type\":\"string\",\"doc\":\"Username\"},{\"name\":\"userid\",\"type\":\"long\",\"doc\":\"User's ID\"},{\"name\":\"created_at\",\"type\":\"long\",\"doc\":\"Time tweet was created\"}],\"doc:\":\"Tweet Schema\"}");

	public static org.apache.avro.Schema getClassSchema() {
		return SCHEMA$;
	}

	/** Tweet text */
	@Deprecated
	public java.lang.CharSequence text;
	/** Tweet id */
	@Deprecated
	public long id;
	/** Entities: symbols */
	@Deprecated
	public java.lang.CharSequence symbols;
	/** Entities: urls */
	@Deprecated
	public java.lang.CharSequence urls;
	/** Entities: hashtags */
	@Deprecated
	public java.lang.CharSequence hashtags;
	/** Entities: user mentions */
	@Deprecated
	public java.lang.CharSequence user_mentions;
	/** Geographic Coordinate: longitude */
	@Deprecated
	public float longitude;
	/** Geographic Coordinate: latitude */
	@Deprecated
	public float latitude;
	/** Username */
	@Deprecated
	public java.lang.CharSequence username;
	/** User's ID */
	@Deprecated
	public long userid;
	/** Time tweet was created */
	@Deprecated
	public long created_at;

	/**
	 * Default constructor. Note that this does not initialize fields to their default values from
	 * the schema. If that is desired then one should use <code>newBuilder()</code>.
	 */
	public Tweet() {
	}

	/**
	 * All-args constructor.
	 */
	public Tweet(java.lang.CharSequence text, java.lang.Long id, java.lang.CharSequence symbols,
			java.lang.CharSequence urls, java.lang.CharSequence hashtags,
			java.lang.CharSequence user_mentions, java.lang.Float longitude,
			java.lang.Float latitude, java.lang.CharSequence username, java.lang.Long userid,
			java.lang.Long created_at) {
		this.text = text;
		this.id = id;
		this.symbols = symbols;
		this.urls = urls;
		this.hashtags = hashtags;
		this.user_mentions = user_mentions;
		this.longitude = longitude;
		this.latitude = latitude;
		this.username = username;
		this.userid = userid;
		this.created_at = created_at;
	}

	public org.apache.avro.Schema getSchema() {
		return SCHEMA$;
	}

	// Used by DatumWriter. Applications should not call.
	public java.lang.Object get(int field$) {
		switch (field$) {
		case 0:
			return text;
		case 1:
			return id;
		case 2:
			return symbols;
		case 3:
			return urls;
		case 4:
			return hashtags;
		case 5:
			return user_mentions;
		case 6:
			return longitude;
		case 7:
			return latitude;
		case 8:
			return username;
		case 9:
			return userid;
		case 10:
			return created_at;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	// Used by DatumReader. Applications should not call.
	@SuppressWarnings(value = "unchecked")
	public void put(int field$, java.lang.Object value$) {
		switch (field$) {
		case 0:
			text = (java.lang.CharSequence) value$;
			break;
		case 1:
			id = (java.lang.Long) value$;
			break;
		case 2:
			symbols = (java.lang.CharSequence) value$;
			break;
		case 3:
			urls = (java.lang.CharSequence) value$;
			break;
		case 4:
			hashtags = (java.lang.CharSequence) value$;
			break;
		case 5:
			user_mentions = (java.lang.CharSequence) value$;
			break;
		case 6:
			longitude = (java.lang.Float) value$;
			break;
		case 7:
			latitude = (java.lang.Float) value$;
			break;
		case 8:
			username = (java.lang.CharSequence) value$;
			break;
		case 9:
			userid = (java.lang.Long) value$;
			break;
		case 10:
			created_at = (java.lang.Long) value$;
			break;
		default:
			throw new org.apache.avro.AvroRuntimeException("Bad index");
		}
	}

	/**
	 * Gets the value of the 'text' field. Tweet text
	 */
	public java.lang.CharSequence getText() {
		return text;
	}

	/**
	 * Sets the value of the 'text' field. Tweet text * @param value the value to set.
	 */
	public void setText(java.lang.CharSequence value) {
		this.text = value;
	}

	/**
	 * Gets the value of the 'id' field. Tweet id
	 */
	public java.lang.Long getId() {
		return id;
	}

	/**
	 * Sets the value of the 'id' field. Tweet id * @param value the value to set.
	 */
	public void setId(java.lang.Long value) {
		this.id = value;
	}

	/**
	 * Gets the value of the 'symbols' field. Entities: symbols
	 */
	public java.lang.CharSequence getSymbols() {
		return symbols;
	}

	/**
	 * Sets the value of the 'symbols' field. Entities: symbols * @param value the value to set.
	 */
	public void setSymbols(java.lang.CharSequence value) {
		this.symbols = value;
	}

	/**
	 * Gets the value of the 'urls' field. Entities: urls
	 */
	public java.lang.CharSequence getUrls() {
		return urls;
	}

	/**
	 * Sets the value of the 'urls' field. Entities: urls * @param value the value to set.
	 */
	public void setUrls(java.lang.CharSequence value) {
		this.urls = value;
	}

	/**
	 * Gets the value of the 'hashtags' field. Entities: hashtags
	 */
	public java.lang.CharSequence getHashtags() {
		return hashtags;
	}

	/**
	 * Sets the value of the 'hashtags' field. Entities: hashtags * @param value the value to set.
	 */
	public void setHashtags(java.lang.CharSequence value) {
		this.hashtags = value;
	}

	/**
	 * Gets the value of the 'user_mentions' field. Entities: user mentions
	 */
	public java.lang.CharSequence getUserMentions() {
		return user_mentions;
	}

	/**
	 * Sets the value of the 'user_mentions' field. Entities: user mentions * @param value the value
	 * to set.
	 */
	public void setUserMentions(java.lang.CharSequence value) {
		this.user_mentions = value;
	}

	/**
	 * Gets the value of the 'longitude' field. Geographic Coordinate: longitude
	 */
	public java.lang.Float getLongitude() {
		return longitude;
	}

	/**
	 * Sets the value of the 'longitude' field. Geographic Coordinate: longitude * @param value the
	 * value to set.
	 */
	public void setLongitude(java.lang.Float value) {
		this.longitude = value;
	}

	/**
	 * Gets the value of the 'latitude' field. Geographic Coordinate: latitude
	 */
	public java.lang.Float getLatitude() {
		return latitude;
	}

	/**
	 * Sets the value of the 'latitude' field. Geographic Coordinate: latitude * @param value the
	 * value to set.
	 */
	public void setLatitude(java.lang.Float value) {
		this.latitude = value;
	}

	/**
	 * Gets the value of the 'username' field. Username
	 */
	public java.lang.CharSequence getUsername() {
		return username;
	}

	/**
	 * Sets the value of the 'username' field. Username * @param value the value to set.
	 */
	public void setUsername(java.lang.CharSequence value) {
		this.username = value;
	}

	/**
	 * Gets the value of the 'userid' field. User's ID
	 */
	public java.lang.Long getUserid() {
		return userid;
	}

	/**
	 * Sets the value of the 'userid' field. User's ID * @param value the value to set.
	 */
	public void setUserid(java.lang.Long value) {
		this.userid = value;
	}

	/**
	 * Gets the value of the 'created_at' field. Time tweet was created
	 */
	public java.lang.Long getCreatedAt() {
		return created_at;
	}

	/**
	 * Sets the value of the 'created_at' field. Time tweet was created * @param value the value to
	 * set.
	 */
	public void setCreatedAt(java.lang.Long value) {
		this.created_at = value;
	}

	/** Creates a new Tweet RecordBuilder */
	public static edu.umbc.idynin1.avro.Tweet.Builder newBuilder() {
		return new edu.umbc.idynin1.avro.Tweet.Builder();
	}

	/** Creates a new Tweet RecordBuilder by copying an existing Builder */
	public static edu.umbc.idynin1.avro.Tweet.Builder newBuilder(edu.umbc.idynin1.avro.Tweet.Builder other) {
		return new edu.umbc.idynin1.avro.Tweet.Builder(other);
	}

	/** Creates a new Tweet RecordBuilder by copying an existing Tweet instance */
	public static edu.umbc.idynin1.avro.Tweet.Builder newBuilder(edu.umbc.idynin1.avro.Tweet other) {
		return new edu.umbc.idynin1.avro.Tweet.Builder(other);
	}

	/**
	 * RecordBuilder for Tweet instances.
	 */
	public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Tweet>
			implements org.apache.avro.data.RecordBuilder<Tweet> {

		private java.lang.CharSequence text;
		private long id;
		private java.lang.CharSequence symbols;
		private java.lang.CharSequence urls;
		private java.lang.CharSequence hashtags;
		private java.lang.CharSequence user_mentions;
		private float longitude;
		private float latitude;
		private java.lang.CharSequence username;
		private long userid;
		private long created_at;

		/** Creates a new Builder */
		private Builder() {
			super(edu.umbc.idynin1.avro.Tweet.SCHEMA$);
		}

		/** Creates a Builder by copying an existing Builder */
		private Builder(edu.umbc.idynin1.avro.Tweet.Builder other) {
			super(other);
			if (isValidValue(fields()[0], other.text)) {
				this.text = data().deepCopy(fields()[0].schema(), other.text);
				fieldSetFlags()[0] = true;
			}
			if (isValidValue(fields()[1], other.id)) {
				this.id = data().deepCopy(fields()[1].schema(), other.id);
				fieldSetFlags()[1] = true;
			}
			if (isValidValue(fields()[2], other.symbols)) {
				this.symbols = data().deepCopy(fields()[2].schema(), other.symbols);
				fieldSetFlags()[2] = true;
			}
			if (isValidValue(fields()[3], other.urls)) {
				this.urls = data().deepCopy(fields()[3].schema(), other.urls);
				fieldSetFlags()[3] = true;
			}
			if (isValidValue(fields()[4], other.hashtags)) {
				this.hashtags = data().deepCopy(fields()[4].schema(), other.hashtags);
				fieldSetFlags()[4] = true;
			}
			if (isValidValue(fields()[5], other.user_mentions)) {
				this.user_mentions = data().deepCopy(fields()[5].schema(), other.user_mentions);
				fieldSetFlags()[5] = true;
			}
			if (isValidValue(fields()[6], other.longitude)) {
				this.longitude = data().deepCopy(fields()[6].schema(), other.longitude);
				fieldSetFlags()[6] = true;
			}
			if (isValidValue(fields()[7], other.latitude)) {
				this.latitude = data().deepCopy(fields()[7].schema(), other.latitude);
				fieldSetFlags()[7] = true;
			}
			if (isValidValue(fields()[8], other.username)) {
				this.username = data().deepCopy(fields()[8].schema(), other.username);
				fieldSetFlags()[8] = true;
			}
			if (isValidValue(fields()[9], other.userid)) {
				this.userid = data().deepCopy(fields()[9].schema(), other.userid);
				fieldSetFlags()[9] = true;
			}
			if (isValidValue(fields()[10], other.created_at)) {
				this.created_at = data().deepCopy(fields()[10].schema(), other.created_at);
				fieldSetFlags()[10] = true;
			}
		}

		/** Creates a Builder by copying an existing Tweet instance */
		private Builder(edu.umbc.idynin1.avro.Tweet other) {
			super(edu.umbc.idynin1.avro.Tweet.SCHEMA$);
			if (isValidValue(fields()[0], other.text)) {
				this.text = data().deepCopy(fields()[0].schema(), other.text);
				fieldSetFlags()[0] = true;
			}
			if (isValidValue(fields()[1], other.id)) {
				this.id = data().deepCopy(fields()[1].schema(), other.id);
				fieldSetFlags()[1] = true;
			}
			if (isValidValue(fields()[2], other.symbols)) {
				this.symbols = data().deepCopy(fields()[2].schema(), other.symbols);
				fieldSetFlags()[2] = true;
			}
			if (isValidValue(fields()[3], other.urls)) {
				this.urls = data().deepCopy(fields()[3].schema(), other.urls);
				fieldSetFlags()[3] = true;
			}
			if (isValidValue(fields()[4], other.hashtags)) {
				this.hashtags = data().deepCopy(fields()[4].schema(), other.hashtags);
				fieldSetFlags()[4] = true;
			}
			if (isValidValue(fields()[5], other.user_mentions)) {
				this.user_mentions = data().deepCopy(fields()[5].schema(), other.user_mentions);
				fieldSetFlags()[5] = true;
			}
			if (isValidValue(fields()[6], other.longitude)) {
				this.longitude = data().deepCopy(fields()[6].schema(), other.longitude);
				fieldSetFlags()[6] = true;
			}
			if (isValidValue(fields()[7], other.latitude)) {
				this.latitude = data().deepCopy(fields()[7].schema(), other.latitude);
				fieldSetFlags()[7] = true;
			}
			if (isValidValue(fields()[8], other.username)) {
				this.username = data().deepCopy(fields()[8].schema(), other.username);
				fieldSetFlags()[8] = true;
			}
			if (isValidValue(fields()[9], other.userid)) {
				this.userid = data().deepCopy(fields()[9].schema(), other.userid);
				fieldSetFlags()[9] = true;
			}
			if (isValidValue(fields()[10], other.created_at)) {
				this.created_at = data().deepCopy(fields()[10].schema(), other.created_at);
				fieldSetFlags()[10] = true;
			}
		}

		/** Gets the value of the 'text' field */
		public java.lang.CharSequence getText() {
			return text;
		}

		/** Sets the value of the 'text' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setText(java.lang.CharSequence value) {
			validate(fields()[0], value);
			this.text = value;
			fieldSetFlags()[0] = true;
			return this;
		}

		/** Checks whether the 'text' field has been set */
		public boolean hasText() {
			return fieldSetFlags()[0];
		}

		/** Clears the value of the 'text' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearText() {
			text = null;
			fieldSetFlags()[0] = false;
			return this;
		}

		/** Gets the value of the 'id' field */
		public java.lang.Long getId() {
			return id;
		}

		/** Sets the value of the 'id' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setId(long value) {
			validate(fields()[1], value);
			this.id = value;
			fieldSetFlags()[1] = true;
			return this;
		}

		/** Checks whether the 'id' field has been set */
		public boolean hasId() {
			return fieldSetFlags()[1];
		}

		/** Clears the value of the 'id' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearId() {
			fieldSetFlags()[1] = false;
			return this;
		}

		/** Gets the value of the 'symbols' field */
		public java.lang.CharSequence getSymbols() {
			return symbols;
		}

		/** Sets the value of the 'symbols' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setSymbols(java.lang.CharSequence value) {
			validate(fields()[2], value);
			this.symbols = value;
			fieldSetFlags()[2] = true;
			return this;
		}

		/** Checks whether the 'symbols' field has been set */
		public boolean hasSymbols() {
			return fieldSetFlags()[2];
		}

		/** Clears the value of the 'symbols' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearSymbols() {
			symbols = null;
			fieldSetFlags()[2] = false;
			return this;
		}

		/** Gets the value of the 'urls' field */
		public java.lang.CharSequence getUrls() {
			return urls;
		}

		/** Sets the value of the 'urls' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setUrls(java.lang.CharSequence value) {
			validate(fields()[3], value);
			this.urls = value;
			fieldSetFlags()[3] = true;
			return this;
		}

		/** Checks whether the 'urls' field has been set */
		public boolean hasUrls() {
			return fieldSetFlags()[3];
		}

		/** Clears the value of the 'urls' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearUrls() {
			urls = null;
			fieldSetFlags()[3] = false;
			return this;
		}

		/** Gets the value of the 'hashtags' field */
		public java.lang.CharSequence getHashtags() {
			return hashtags;
		}

		/** Sets the value of the 'hashtags' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setHashtags(java.lang.CharSequence value) {
			validate(fields()[4], value);
			this.hashtags = value;
			fieldSetFlags()[4] = true;
			return this;
		}

		/** Checks whether the 'hashtags' field has been set */
		public boolean hasHashtags() {
			return fieldSetFlags()[4];
		}

		/** Clears the value of the 'hashtags' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearHashtags() {
			hashtags = null;
			fieldSetFlags()[4] = false;
			return this;
		}

		/** Gets the value of the 'user_mentions' field */
		public java.lang.CharSequence getUserMentions() {
			return user_mentions;
		}

		/** Sets the value of the 'user_mentions' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setUserMentions(java.lang.CharSequence value) {
			validate(fields()[5], value);
			this.user_mentions = value;
			fieldSetFlags()[5] = true;
			return this;
		}

		/** Checks whether the 'user_mentions' field has been set */
		public boolean hasUserMentions() {
			return fieldSetFlags()[5];
		}

		/** Clears the value of the 'user_mentions' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearUserMentions() {
			user_mentions = null;
			fieldSetFlags()[5] = false;
			return this;
		}

		/** Gets the value of the 'longitude' field */
		public java.lang.Float getLongitude() {
			return longitude;
		}

		/** Sets the value of the 'longitude' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setLongitude(float value) {
			validate(fields()[6], value);
			this.longitude = value;
			fieldSetFlags()[6] = true;
			return this;
		}

		/** Checks whether the 'longitude' field has been set */
		public boolean hasLongitude() {
			return fieldSetFlags()[6];
		}

		/** Clears the value of the 'longitude' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearLongitude() {
			fieldSetFlags()[6] = false;
			return this;
		}

		/** Gets the value of the 'latitude' field */
		public java.lang.Float getLatitude() {
			return latitude;
		}

		/** Sets the value of the 'latitude' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setLatitude(float value) {
			validate(fields()[7], value);
			this.latitude = value;
			fieldSetFlags()[7] = true;
			return this;
		}

		/** Checks whether the 'latitude' field has been set */
		public boolean hasLatitude() {
			return fieldSetFlags()[7];
		}

		/** Clears the value of the 'latitude' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearLatitude() {
			fieldSetFlags()[7] = false;
			return this;
		}

		/** Gets the value of the 'username' field */
		public java.lang.CharSequence getUsername() {
			return username;
		}

		/** Sets the value of the 'username' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setUsername(java.lang.CharSequence value) {
			validate(fields()[8], value);
			this.username = value;
			fieldSetFlags()[8] = true;
			return this;
		}

		/** Checks whether the 'username' field has been set */
		public boolean hasUsername() {
			return fieldSetFlags()[8];
		}

		/** Clears the value of the 'username' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearUsername() {
			username = null;
			fieldSetFlags()[8] = false;
			return this;
		}

		/** Gets the value of the 'userid' field */
		public java.lang.Long getUserid() {
			return userid;
		}

		/** Sets the value of the 'userid' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setUserid(long value) {
			validate(fields()[9], value);
			this.userid = value;
			fieldSetFlags()[9] = true;
			return this;
		}

		/** Checks whether the 'userid' field has been set */
		public boolean hasUserid() {
			return fieldSetFlags()[9];
		}

		/** Clears the value of the 'userid' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearUserid() {
			fieldSetFlags()[9] = false;
			return this;
		}

		/** Gets the value of the 'created_at' field */
		public java.lang.Long getCreatedAt() {
			return created_at;
		}

		/** Sets the value of the 'created_at' field */
		public edu.umbc.idynin1.avro.Tweet.Builder setCreatedAt(long value) {
			validate(fields()[10], value);
			this.created_at = value;
			fieldSetFlags()[10] = true;
			return this;
		}

		/** Checks whether the 'created_at' field has been set */
		public boolean hasCreatedAt() {
			return fieldSetFlags()[10];
		}

		/** Clears the value of the 'created_at' field */
		public edu.umbc.idynin1.avro.Tweet.Builder clearCreatedAt() {
			fieldSetFlags()[10] = false;
			return this;
		}

		@Override
		public Tweet build() {
			try {
				Tweet record = new Tweet();
				record.text = fieldSetFlags()[0] ? this.text
						: (java.lang.CharSequence) defaultValue(fields()[0]);
				record.id = fieldSetFlags()[1] ? this.id
						: (java.lang.Long) defaultValue(fields()[1]);
				record.symbols = fieldSetFlags()[2] ? this.symbols
						: (java.lang.CharSequence) defaultValue(fields()[2]);
				record.urls = fieldSetFlags()[3] ? this.urls
						: (java.lang.CharSequence) defaultValue(fields()[3]);
				record.hashtags = fieldSetFlags()[4] ? this.hashtags
						: (java.lang.CharSequence) defaultValue(fields()[4]);
				record.user_mentions = fieldSetFlags()[5] ? this.user_mentions
						: (java.lang.CharSequence) defaultValue(fields()[5]);
				record.longitude = fieldSetFlags()[6] ? this.longitude
						: (java.lang.Float) defaultValue(fields()[6]);
				record.latitude = fieldSetFlags()[7] ? this.latitude
						: (java.lang.Float) defaultValue(fields()[7]);
				record.username = fieldSetFlags()[8] ? this.username
						: (java.lang.CharSequence) defaultValue(fields()[8]);
				record.userid = fieldSetFlags()[9] ? this.userid
						: (java.lang.Long) defaultValue(fields()[9]);
				record.created_at = fieldSetFlags()[10] ? this.created_at
						: (java.lang.Long) defaultValue(fields()[10]);
				return record;
			} catch (Exception e) {
				throw new org.apache.avro.AvroRuntimeException(e);
			}
		}
	}
}
