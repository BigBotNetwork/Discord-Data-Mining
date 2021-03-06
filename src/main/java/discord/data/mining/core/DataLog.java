package discord.data.mining.core;

import discord.data.mining.DataMining;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.store.StoreChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.store.update.StoreChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateTopicEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateBoostTimeEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.update.*;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.user.UserActivityEndEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.events.user.update.*;

import java.awt.*;
import java.time.Instant;

public class DataLog {

    public static void log(GenericEvent event) {
        JDA BOT = DataMining.bots.get(DataMining.bot);
        DataMining.bot++;
        if (DataMining.bot == DataMining.bots.size()) DataMining.bot = 0;
        if (event instanceof MessageReceivedEvent) {
            MessageReceivedEvent msgevent = (MessageReceivedEvent) event;
            if (!msgevent.getGuild().getId().equals("448554629282922527")) {
                BOT.getTextChannelById(DataMining.MessageLog).sendMessage(new EmbedUtil().add()
                        .setThumbnail(msgevent.getGuild().getIconUrl())
                        .setAuthor(msgevent.getAuthor().getAsTag(), msgevent.getAuthor().getAvatarUrl(), msgevent.getAuthor().getAvatarUrl())
                        .setTitle("New Message")
                        .addField("Guild name", msgevent.getGuild().getName(), true)
                        .addField("Guild ID", msgevent.getGuild().getId(), true)
                        .addField("Guild owner", msgevent.getGuild().getOwner().getUser().getAsTag(), true)
                        .addField("Content", "```" + msgevent.getMessage().getContentDisplay() + "```", false)
                        .build()).queue();
            }
        } else if (event instanceof MessageDeleteEvent) {
            MessageDeleteEvent msgevent = (MessageDeleteEvent) event;
            if (!msgevent.getGuild().getId().equals("448554629282922527")) {
                BOT.getTextChannelById(DataMining.MessageLog).sendMessage(new EmbedUtil().remove()
                        .setThumbnail(msgevent.getGuild().getIconUrl())
                        .setTitle("Message deleted")
                        .addField("Guild name", msgevent.getGuild().getName(), true)
                        .addField("Guild ID", msgevent.getGuild().getId(), true)
                        .addField("Guild owner", msgevent.getGuild().getOwner().getUser().getAsTag(), true)
                        .build()).queue();
            }
        } else if (event instanceof MessageReactionAddEvent) {
            MessageReactionAddEvent revent = (MessageReactionAddEvent) event;
            if (!revent.getGuild().getId().equals("448554629282922527")) {
                BOT.getTextChannelById(DataMining.ReactionLog).sendMessage(new EmbedUtil().add()
                        .setThumbnail(revent.getGuild().getIconUrl())
                        .setAuthor(revent.getMember().getUser().getAsTag(), revent.getMember().getUser().getAvatarUrl(), revent.getMember().getUser().getAvatarUrl())
                        .setTitle("Reaction added")
                        .addField("Guild name", revent.getGuild().getName(), true)
                        .addField("Guild ID", revent.getGuild().getId(), true)
                        .addField("Guild owner", revent.getGuild().getOwner().getUser().getAsTag(), true)
                        .addField("Reaction", revent.getReaction().toString(), false)
                        .build()).queue();
            }
        } else if (event instanceof MessageReactionRemoveEvent) {
            MessageReactionRemoveEvent revent = (MessageReactionRemoveEvent) event;
            if (!revent.getGuild().getId().equals("448554629282922527")) {
                BOT.getTextChannelById(DataMining.ReactionLog).sendMessage(new EmbedUtil().remove()
                        .setThumbnail(revent.getGuild().getIconUrl())
                        .setAuthor(revent.getMember().getUser().getAsTag(), revent.getMember().getUser().getAvatarUrl(), revent.getMember().getUser().getAvatarUrl())
                        .setTitle("Reaction removed")
                        .addField("Guild name", revent.getGuild().getName(), true)
                        .addField("Guild ID", revent.getGuild().getId(), true)
                        .addField("Guild owner", revent.getGuild().getOwner().getUser().getAsTag(), true)
                        .addField("Reaction", revent.getReaction().toString(), false)
                        .build()).queue();
            }
        } else if (event instanceof UserUpdateNameEvent) {
            UserUpdateNameEvent uevent = (UserUpdateNameEvent) event;
            BOT.getTextChannelById(DataMining.UserLog).sendMessage(new EmbedUtil().add()
                    .setThumbnail(uevent.getUser().getAvatarUrl())
                    .setAuthor(uevent.getUser().getAsTag(), uevent.getUser().getAvatarUrl(), uevent.getUser().getAvatarUrl())
                    .setTitle("User updated name")
                    .addField("Old name", uevent.getOldName(), true)
                    .addField("New name", uevent.getNewName(), true)
                    .build()).queue();
        } else if (event instanceof UserUpdateDiscriminatorEvent) {
            UserUpdateDiscriminatorEvent uevent = (UserUpdateDiscriminatorEvent) event;
            BOT.getTextChannelById(DataMining.UserLog).sendMessage(new EmbedUtil().add()
                    .setThumbnail(uevent.getUser().getAvatarUrl())
                    .setAuthor(uevent.getUser().getAsTag(), uevent.getUser().getAvatarUrl(), uevent.getUser().getAvatarUrl())
                    .setTitle("User updated discriminator")
                    .addField("Old discriminator", uevent.getOldDiscriminator(), true)
                    .addField("New discriminator", uevent.getNewDiscriminator(), true)
                    .build()).queue();
        } else if (event instanceof UserUpdateAvatarEvent) {
            UserUpdateAvatarEvent uevent = (UserUpdateAvatarEvent) event;
            try {
                BOT.getTextChannelById(DataMining.UserLog).sendMessage(new EmbedUtil().add()
                        .setImage(uevent.getNewAvatarUrl())
                        .setThumbnail(uevent.getOldAvatarUrl())
                        .setAuthor(uevent.getUser().getAsTag(), uevent.getUser().getAvatarUrl(), uevent.getUser().getAvatarUrl())
                        .setTitle("User updated avatar")
                        .addField("New avatar ID", uevent.getNewAvatarId(), true)
                        .addField("Old avatar ID", uevent.getOldAvatarId(), true)
                        .build()).queue();
            } catch (Exception ignore){}
        } else if (event instanceof UserUpdateOnlineStatusEvent) {
            UserUpdateOnlineStatusEvent uevent = (UserUpdateOnlineStatusEvent) event;
            BOT.getTextChannelById(DataMining.UserLog).sendMessage(new EmbedUtil().add()
                    .setThumbnail(uevent.getUser().getAvatarUrl())
                    .setAuthor(uevent.getUser().getAsTag(), uevent.getUser().getAvatarUrl(), uevent.getUser().getAvatarUrl())
                    .setTitle("User updated online status")
                    .addField("New online status", uevent.getNewOnlineStatus().toString(), true)
                    .addField("Old online status", uevent.getOldOnlineStatus().toString(), true)
                    .build()).queue();
        } else if (event instanceof UserActivityStartEvent) {
            UserActivityStartEvent uevent = (UserActivityStartEvent) event;
            BOT.getTextChannelById(DataMining.ActivityLog).sendMessage(new EmbedUtil().add()
                    .setThumbnail(uevent.getUser().getAvatarUrl())
                    .setAuthor(uevent.getUser().getAsTag(), uevent.getUser().getAvatarUrl(), uevent.getUser().getAvatarUrl())
                    .setTitle("User started playing")
                    .addField("New activity", uevent.getNewActivity().toString(), true)
                    .build()).queue();
        } else if (event instanceof UserActivityEndEvent) {
            UserActivityEndEvent uevent = (UserActivityEndEvent) event;
            BOT.getTextChannelById(DataMining.ActivityLog).sendMessage(new EmbedUtil().remove()
                    .setThumbnail(uevent.getUser().getAvatarUrl())
                    .setAuthor(uevent.getUser().getAsTag(), uevent.getUser().getAvatarUrl(), uevent.getUser().getAvatarUrl())
                    .setTitle("User ended playing")
                    .addField("Old activity", uevent.getOldActivity().toString(), true)
                    .build()).queue();
        } else if (event instanceof StoreChannelCreateEvent) {
            StoreChannelCreateEvent cevent = (StoreChannelCreateEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedUtil().add()
                    .setThumbnail(cevent.getChannel().getGuild().getIconUrl())
                    .setAuthor(cevent.getChannel().getGuild().getName(), cevent.getChannel().getGuild().getIconUrl(), cevent.getChannel().getGuild().getIconUrl())
                    .setTitle("Store channel created")
                    .addField("Guild name", cevent.getChannel().getGuild().getName(), true)
                    .addField("Guild ID", cevent.getChannel().getGuild().getId(), true)
                    .addField("Guild owner", cevent.getChannel().getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Channel name", cevent.getChannel().getName(), true)
                    .addField("Channel ID", cevent.getChannel().getId(), true)
                    .build()).queue();
        } else if (event instanceof StoreChannelDeleteEvent) {
            StoreChannelDeleteEvent cevent = (StoreChannelDeleteEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedUtil().remove()
                    .setThumbnail(cevent.getChannel().getGuild().getIconUrl())
                    .setAuthor(cevent.getChannel().getGuild().getName(), cevent.getChannel().getGuild().getIconUrl(), cevent.getChannel().getGuild().getIconUrl())
                    .setTitle("Store channel deleted")
                    .addField("Guild name", cevent.getChannel().getGuild().getName(), true)
                    .addField("Guild ID", cevent.getChannel().getGuild().getId(), true)
                    .addField("Guild owner", cevent.getChannel().getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Channel name", cevent.getChannel().getName(), true)
                    .addField("Channel ID", cevent.getChannel().getId(), true)
                    .build()).queue();
        } else if (event instanceof StoreChannelUpdateNameEvent) {
            StoreChannelUpdateNameEvent cevent = (StoreChannelUpdateNameEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedUtil().update()
                    .setThumbnail(cevent.getChannel().getGuild().getIconUrl())
                    .setAuthor(cevent.getChannel().getGuild().getName(), cevent.getChannel().getGuild().getIconUrl(), cevent.getChannel().getGuild().getIconUrl())
                    .setTitle("Store channel name updated")
                    .addField("Guild name", cevent.getChannel().getGuild().getName(), true)
                    .addField("Guild ID", cevent.getChannel().getGuild().getId(), true)
                    .addField("Guild owner", cevent.getChannel().getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old name", cevent.getOldName(), true)
                    .addField("New name", cevent.getNewName(), true)
                    .addField("Channel ID", cevent.getChannel().getId(), true)
                    .build()).queue();
        } else if (event instanceof TextChannelCreateEvent) {
            TextChannelCreateEvent cevent = (TextChannelCreateEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(cevent.getChannel().getGuild().getIconUrl())
                    .setAuthor(cevent.getChannel().getGuild().getName(), cevent.getChannel().getGuild().getIconUrl(), cevent.getChannel().getGuild().getIconUrl())
                    .setTitle("Text channel created")
                    .addField("Guild name", cevent.getChannel().getGuild().getName(), true)
                    .addField("Guild ID", cevent.getChannel().getGuild().getId(), true)
                    .addField("Guild owner", cevent.getChannel().getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Channel name", cevent.getChannel().getName(), true)
                    .addField("Channel ID", cevent.getChannel().getId(), true)
                    .setFooter("BBN Data Mining", "https://bbn.one/images/avatar.png")
                    .build()).queue();
        } else if (event instanceof TextChannelDeleteEvent) {
            TextChannelDeleteEvent cevent = (TextChannelDeleteEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTimestamp(Instant.now())
                    .setThumbnail(cevent.getChannel().getGuild().getIconUrl())
                    .setAuthor(cevent.getChannel().getGuild().getName(), cevent.getChannel().getGuild().getIconUrl(), cevent.getChannel().getGuild().getIconUrl())
                    .setTitle("Text channel deleted")
                    .addField("Guild name", cevent.getChannel().getGuild().getName(), true)
                    .addField("Guild ID", cevent.getChannel().getGuild().getId(), true)
                    .addField("Guild owner", cevent.getChannel().getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Channel name", cevent.getChannel().getName(), true)
                    .addField("Channel ID", cevent.getChannel().getId(), true)
                    .setFooter("BBN Data Mining", "https://bbn.one/images/avatar.png")
                    .build()).queue();
        } else if (event instanceof TextChannelUpdateNameEvent) {
            TextChannelUpdateNameEvent cevent = (TextChannelUpdateNameEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.YELLOW)
                    .setTimestamp(Instant.now())
                    .setThumbnail(cevent.getChannel().getGuild().getIconUrl())
                    .setAuthor(cevent.getChannel().getGuild().getName(), cevent.getChannel().getGuild().getIconUrl(), cevent.getChannel().getGuild().getIconUrl())
                    .setTitle("Text channel name updated")
                    .addField("Guild name", cevent.getChannel().getGuild().getName(), true)
                    .addField("Guild ID", cevent.getChannel().getGuild().getId(), true)
                    .addField("Guild owner", cevent.getChannel().getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old name", cevent.getOldName(), true)
                    .addField("New name", cevent.getNewName(), true)
                    .addField("Channel ID", cevent.getChannel().getId(), true)
                    .setFooter("BBN Data Mining", "https://bbn.one/images/avatar.png")
                    .build()).queue();
        } else if (event instanceof TextChannelUpdateTopicEvent) {
            TextChannelUpdateTopicEvent cevent = (TextChannelUpdateTopicEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.YELLOW)
                    .setTimestamp(Instant.now())
                    .setThumbnail(cevent.getChannel().getGuild().getIconUrl())
                    .setAuthor(cevent.getChannel().getGuild().getName(), cevent.getChannel().getGuild().getIconUrl(), cevent.getChannel().getGuild().getIconUrl())
                    .setTitle("Text channel topic updated")
                    .addField("Guild name", cevent.getChannel().getGuild().getName(), true)
                    .addField("Guild ID", cevent.getChannel().getGuild().getId(), true)
                    .addField("Guild owner", cevent.getChannel().getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Channel name", cevent.getChannel().getName(), true)
                    .addField("Old topic", cevent.getOldTopic(), true)
                    .addField("New topic", cevent.getNewTopic(), true)
                    .addField("Channel ID", cevent.getChannel().getId(), true)
                    .setFooter("BBN Data Mining", "https://bbn.one/images/avatar.png")
                    .build()).queue();
        } else if (event instanceof VoiceChannelCreateEvent) {
            VoiceChannelCreateEvent cevent = (VoiceChannelCreateEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(cevent.getChannel().getGuild().getIconUrl())
                    .setAuthor(cevent.getChannel().getGuild().getName(), cevent.getChannel().getGuild().getIconUrl(), cevent.getChannel().getGuild().getIconUrl())
                    .setTitle("Voice channel created")
                    .addField("Guild name", cevent.getChannel().getGuild().getName(), true)
                    .addField("Guild ID", cevent.getChannel().getGuild().getId(), true)
                    .addField("Guild owner", cevent.getChannel().getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Channel name", cevent.getChannel().getName(), true)
                    .addField("Channel ID", cevent.getChannel().getId(), true)
                    .setFooter("BBN Data Mining", "https://bbn.one/images/avatar.png")
                    .build()).queue();
        } else if (event instanceof VoiceChannelDeleteEvent) {
            VoiceChannelDeleteEvent cevent = (VoiceChannelDeleteEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTimestamp(Instant.now())
                    .setThumbnail(cevent.getChannel().getGuild().getIconUrl())
                    .setAuthor(cevent.getChannel().getGuild().getName(), cevent.getChannel().getGuild().getIconUrl(), cevent.getChannel().getGuild().getIconUrl())
                    .setTitle("Voice channel deleted")
                    .addField("Guild name", cevent.getChannel().getGuild().getName(), true)
                    .addField("Guild ID", cevent.getChannel().getGuild().getId(), true)
                    .addField("Guild owner", cevent.getChannel().getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Channel name", cevent.getChannel().getName(), true)
                    .addField("Channel ID", cevent.getChannel().getId(), true)
                    .setFooter("BBN Data Mining", "https://bbn.one/images/avatar.png")
                    .build()).queue();
        } else if (event instanceof VoiceChannelUpdateNameEvent) {
            VoiceChannelUpdateNameEvent cevent = (VoiceChannelUpdateNameEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.YELLOW)
                    .setTimestamp(Instant.now())
                    .setThumbnail(cevent.getChannel().getGuild().getIconUrl())
                    .setAuthor(cevent.getChannel().getGuild().getName(), cevent.getChannel().getGuild().getIconUrl(), cevent.getChannel().getGuild().getIconUrl())
                    .setTitle("Voice channel name updated")
                    .addField("Guild name", cevent.getChannel().getGuild().getName(), true)
                    .addField("Guild ID", cevent.getChannel().getGuild().getId(), true)
                    .addField("Guild owner", cevent.getChannel().getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old name", cevent.getOldName(), true)
                    .addField("New name", cevent.getNewName(), true)
                    .addField("Channel ID", cevent.getChannel().getId(), true)
                    .setFooter("BBN Data Mining", "https://bbn.one/images/avatar.png")
                    .build()).queue();
        } else if (event instanceof CategoryCreateEvent) {
            CategoryCreateEvent cevent = (CategoryCreateEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(cevent.getGuild().getIconUrl())
                    .setAuthor(cevent.getGuild().getName(), cevent.getGuild().getIconUrl(), cevent.getGuild().getIconUrl())
                    .setTitle("Category created")
                    .addField("Guild name", cevent.getGuild().getName(), true)
                    .addField("Guild ID", cevent.getGuild().getId(), true)
                    .addField("Guild owner", cevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Category name", cevent.getCategory().getName(), true)
                    .addField("Category ID", cevent.getCategory().getId(), true)
                    .setFooter("BBN Data Mining", "https://bbn.one/images/avatar.png")
                    .build()).queue();
        } else if (event instanceof CategoryDeleteEvent) {
            CategoryDeleteEvent cevent = (CategoryDeleteEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTimestamp(Instant.now())
                    .setThumbnail(cevent.getGuild().getIconUrl())
                    .setAuthor(cevent.getGuild().getName(), cevent.getGuild().getIconUrl(), cevent.getGuild().getIconUrl())
                    .setTitle("Category deleted")
                    .addField("Guild name", cevent.getGuild().getName(), true)
                    .addField("Guild ID", cevent.getGuild().getId(), true)
                    .addField("Guild owner", cevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Category name", cevent.getCategory().getName(), true)
                    .addField("Category ID", cevent.getCategory().getId(), true)
                    .setFooter("BBN Data Mining", "https://bbn.one/images/avatar.png")
                    .build()).queue();
        } else if (event instanceof CategoryUpdateNameEvent) {
            CategoryUpdateNameEvent cevent = (CategoryUpdateNameEvent) event;
            BOT.getTextChannelById(DataMining.ChannelLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.YELLOW)
                    .setTimestamp(Instant.now())
                    .setThumbnail(cevent.getGuild().getIconUrl())
                    .setAuthor(cevent.getGuild().getName(), cevent.getGuild().getIconUrl(), cevent.getGuild().getIconUrl())
                    .setTitle("Category name updated")
                    .addField("Guild name", cevent.getGuild().getName(), true)
                    .addField("Guild ID", cevent.getGuild().getId(), true)
                    .addField("Guild owner", cevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old name", cevent.getOldName(), true)
                    .addField("New name", cevent.getNewName(), true)
                    .addField("Category ID", cevent.getCategory().getId(), true)
                    .setFooter("BBN Data Mining", "https://bbn.one/images/avatar.png")
                    .build()).queue();
        } else if (event instanceof GuildBanEvent) {
            GuildBanEvent gevent = (GuildBanEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getUser().getAsTag(), gevent.getUser().getAvatarUrl(), gevent.getUser().getAvatarUrl())
                    .setTitle("User banned")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("User ID", gevent.getUser().getId(), true)
                    .build()).queue();
        } else if (event instanceof GuildUnbanEvent) {
            GuildUnbanEvent gevent = (GuildUnbanEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getUser().getAsTag(), gevent.getUser().getAvatarUrl(), gevent.getUser().getAvatarUrl())
                    .setTitle("User unbanned")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("User ID", gevent.getUser().getId(), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateAfkChannelEvent) {
            GuildUpdateAfkChannelEvent gevent = (GuildUpdateAfkChannelEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("AFK channel updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old channel", String.valueOf(gevent.getOldAfkChannel()), true)
                    .addField("New channel", String.valueOf(gevent.getNewAfkChannel()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateSystemChannelEvent) {
            GuildUpdateSystemChannelEvent gevent = (GuildUpdateSystemChannelEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("System channel updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old channel", String.valueOf(gevent.getOldSystemChannel()), true)
                    .addField("New channel", String.valueOf(gevent.getNewSystemChannel()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateAfkTimeoutEvent) {
            GuildUpdateAfkTimeoutEvent gevent = (GuildUpdateAfkTimeoutEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("AFk timeout updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old channel", String.valueOf(gevent.getOldAfkTimeout()), true)
                    .addField("New channel", String.valueOf(gevent.getNewAfkTimeout()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateExplicitContentLevelEvent) {
            GuildUpdateExplicitContentLevelEvent gevent = (GuildUpdateExplicitContentLevelEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Explicit content level updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old level", String.valueOf(gevent.getOldLevel()), true)
                    .addField("New level", String.valueOf(gevent.getNewLevel()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateIconEvent) {
            GuildUpdateIconEvent gevent = (GuildUpdateIconEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getOldIconUrl())
                    .setImage(gevent.getNewIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Icon updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("New icon ID", gevent.getOldIconId(), true)
                    .addField("Old icon ID", gevent.getNewIconId(), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateMFALevelEvent) {
            GuildUpdateMFALevelEvent gevent = (GuildUpdateMFALevelEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("MFA level updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old MFA level", String.valueOf(gevent.getOldMFALevel()), true)
                    .addField("New MFA level", String.valueOf(gevent.getNewMFALevel()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateNameEvent) {
            GuildUpdateNameEvent gevent = (GuildUpdateNameEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Guild name updated")
                    .addField("Old name", gevent.getOldName(), true)
                    .addField("New name", gevent.getNewName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateNotificationLevelEvent) {
            GuildUpdateNotificationLevelEvent gevent = (GuildUpdateNotificationLevelEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Notification level updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old level", String.valueOf(gevent.getOldNotificationLevel()), true)
                    .addField("New level", String.valueOf(gevent.getNewNotificationLevel()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateOwnerEvent) {
            GuildUpdateOwnerEvent gevent = (GuildUpdateOwnerEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Owner updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Old owner", gevent.getOldOwner().getUser().getAsTag(), true)
                    .addField("New owner", gevent.getNewOwner().getUser().getAsTag(), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateRegionEvent) {
            GuildUpdateRegionEvent gevent = (GuildUpdateRegionEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Region updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old region", String.valueOf(gevent.getOldRegion()), true)
                    .addField("New region", String.valueOf(gevent.getNewRegion()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateSplashEvent) {
            GuildUpdateSplashEvent gevent = (GuildUpdateSplashEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getOldSplashUrl())
                    .setImage(gevent.getNewSplashUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Splash updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old ID", gevent.getOldSplashId(), true)
                    .addField("New ID", gevent.getNewSplashId(), true)
                    .build()).queue();
        } else if (event instanceof  GuildUpdateVerificationLevelEvent) {
            GuildUpdateVerificationLevelEvent gevent = (GuildUpdateVerificationLevelEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Verification level updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old level", String.valueOf(gevent.getOldVerificationLevel()), true)
                    .addField("New level", String.valueOf(gevent.getNewVerificationLevel()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateFeaturesEvent){
            GuildUpdateFeaturesEvent gevent = (GuildUpdateFeaturesEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Features updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old features", String.valueOf(gevent.getOldFeatures()), true)
                    .addField("New features", String.valueOf(gevent.getNewFeatures()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateVanityCodeEvent){
            GuildUpdateVanityCodeEvent gevent = (GuildUpdateVanityCodeEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Vanity code updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old URL", gevent.getOldVanityUrl(), true)
                    .addField("New URL", gevent.getNewVanityUrl(), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateBannerEvent){
            GuildUpdateBannerEvent gevent = (GuildUpdateBannerEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getOldBannerUrl())
                    .setImage(gevent.getNewBannerIdUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Banner updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old ID", gevent.getOldBannerId(), true)
                    .addField("New ID", gevent.getNewBannerId(), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateDescriptionEvent){
            GuildUpdateDescriptionEvent gevent = (GuildUpdateDescriptionEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Description updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old description", gevent.getOldDescription(), true)
                    .addField("New description", gevent.getNewDescription(), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateBoostTierEvent){
            GuildUpdateBoostTierEvent gevent = (GuildUpdateBoostTierEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Boost tier changed")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old tier", String.valueOf(gevent.getOldBoostTier()), true)
                    .addField("New tier", String.valueOf(gevent.getNewBoostTier()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateBoostCountEvent){
            GuildUpdateBoostCountEvent gevent = (GuildUpdateBoostCountEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Boost added")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old boost count", String.valueOf(gevent.getOldBoostCount()), true)
                    .addField("New boost count", String.valueOf(gevent.getNewBoostCount()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateMaxMembersEvent){
            GuildUpdateMaxMembersEvent gevent = (GuildUpdateMaxMembersEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Max. members updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old max members", String.valueOf(gevent.getOldMaxMembers()), true)
                    .addField("New max members", String.valueOf(gevent.getNewMaxMembers()), true)
                    .build()).queue();
        } else if (event instanceof GuildUpdateMaxPresencesEvent){
            GuildUpdateMaxPresencesEvent gevent = (GuildUpdateMaxPresencesEvent) event;
            BOT.getTextChannelById(DataMining.GuildLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getGuild().getIconUrl())
                    .setAuthor(gevent.getGuild().getName(), gevent.getGuild().getIconUrl(), gevent.getGuild().getIconUrl())
                    .setTitle("Max. presences updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Old max presences", String.valueOf(gevent.getOldMaxPresences()), true)
                    .addField("New max presences", String.valueOf(gevent.getNewMaxPresences()), true)
                    .build()).queue();
        } else if (event instanceof GuildMemberJoinEvent){
            GuildMemberJoinEvent gevent = (GuildMemberJoinEvent) event;
            BOT.getTextChannelById(DataMining.MemberLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getUser().getAvatarUrl())
                    .setAuthor(gevent.getUser().getAsTag(), gevent.getUser().getAvatarUrl(), gevent.getUser().getAvatarUrl())
                    .setTitle("Member joined")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Member ID", gevent.getMember().getId(), true)
                    .build()).queue();
        } else if (event instanceof GuildMemberLeaveEvent){
            GuildMemberLeaveEvent gevent = (GuildMemberLeaveEvent) event;
            BOT.getTextChannelById(DataMining.MemberLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getUser().getAvatarUrl())
                    .setAuthor(gevent.getUser().getAsTag(), gevent.getUser().getAvatarUrl(), gevent.getUser().getAvatarUrl())
                    .setTitle("Member left")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Member ID", gevent.getMember().getId(), true)
                    .build()).queue();
        } else if (event instanceof GuildMemberRoleAddEvent){
            GuildMemberRoleAddEvent gevent = (GuildMemberRoleAddEvent) event;
            BOT.getTextChannelById(DataMining.MemberLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getUser().getAvatarUrl())
                    .setAuthor(gevent.getUser().getAsTag(), gevent.getUser().getAvatarUrl(), gevent.getUser().getAvatarUrl())
                    .setTitle("Role added")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Member ID", gevent.getMember().getId(), true)
                    .addField("Role", gevent.getRoles().toString(), true)
                    .build()).queue();
        } else if (event instanceof GuildMemberRoleRemoveEvent){
            GuildMemberRoleRemoveEvent gevent = (GuildMemberRoleRemoveEvent) event;
            BOT.getTextChannelById(DataMining.MemberLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.RED)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getUser().getAvatarUrl())
                    .setAuthor(gevent.getUser().getAsTag(), gevent.getUser().getAvatarUrl(), gevent.getUser().getAvatarUrl())
                    .setTitle("Role removed")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Member ID", gevent.getMember().getId(), true)
                    .addField("Role", gevent.getRoles().toString(), true)
                    .build()).queue();
        } else if (event instanceof GuildMemberUpdateNicknameEvent){
            GuildMemberUpdateNicknameEvent gevent = (GuildMemberUpdateNicknameEvent) event;
            BOT.getTextChannelById(DataMining.MemberLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getUser().getAvatarUrl())
                    .setAuthor(gevent.getUser().getAsTag(), gevent.getUser().getAvatarUrl(), gevent.getUser().getAvatarUrl())
                    .setTitle("Nickname updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Member ID", gevent.getMember().getId(), true)
                    .addField("Old nickname", gevent.getOldNickname(), true)
                    .addField("New nickname", gevent.getNewNickname(), true)
                    .build()).queue();
        } else if (event instanceof GuildMemberUpdateBoostTimeEvent){
            GuildMemberUpdateBoostTimeEvent gevent = (GuildMemberUpdateBoostTimeEvent) event;
            BOT.getTextChannelById(DataMining.MemberLog).sendMessage(new EmbedBuilder()
                    .setColor(Color.GREEN)
                    .setTimestamp(Instant.now())
                    .setThumbnail(gevent.getUser().getAvatarUrl())
                    .setAuthor(gevent.getUser().getAsTag(), gevent.getUser().getAvatarUrl(), gevent.getUser().getAvatarUrl())
                    .setTitle("Boost time updated")
                    .addField("Guild name", gevent.getGuild().getName(), true)
                    .addField("Guild ID", gevent.getGuild().getId(), true)
                    .addField("Guild owner", gevent.getGuild().getOwner().getUser().getAsTag(), true)
                    .addField("Member ID", gevent.getMember().getId(), true)
                    .addField("Old time", String.valueOf(gevent.getOldTimeBoosted()), true)
                    .addField("New time", String.valueOf(gevent.getNewTimeBoosted()), true)
                    .build()).queue();
        }
    }
}
